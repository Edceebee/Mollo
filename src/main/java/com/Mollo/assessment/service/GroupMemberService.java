package com.Mollo.assessment.service;

import com.Mollo.assessment.entities.EsusuGroup;
import com.Mollo.assessment.entities.GroupMember;
import com.Mollo.assessment.entities.dtos.UpdateGroupMemberDto;
import com.Mollo.assessment.repositories.GroupMembersRepository;
import com.Mollo.assessment.repositories.EsusuGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupMemberService {

    @Autowired
    GroupMembersRepository groupMembersRepository;

    @Autowired
    EsusuGroupRepository esusuGroupRepository;

    @Autowired
    ModelMapper modelMapper;


    public GroupMember registerGroupMember(GroupMember groupMember) {

        groupMembersRepository.save(groupMember);
        return groupMember;
    }


    public GroupMember updateGroupMember(Long groupMemberId, UpdateGroupMemberDto updateGroupMemberDto) {

        GroupMember groupMember = groupMembersRepository.findById(groupMemberId).orElse(null);
        if (groupMember == null) {
            throw new IllegalArgumentException("Member id does not exist");
        }
        EsusuGroup findByName = esusuGroupRepository.findByGroupName(updateGroupMemberDto.getNameOfGroup()).orElse(null);
            if (findByName == null) {
                throw new IllegalArgumentException("Group with name: "+updateGroupMemberDto.getNameOfGroup()+ " does not exist");
            }

        modelMapper.map(updateGroupMemberDto, groupMember);

        if (updateGroupMemberDto.getAmountToSave() < findByName.getWeeklySaving() ||
            updateGroupMemberDto.getAmountToSave() > findByName.getWeeklySaving()) {
            throw new IllegalArgumentException("You have to save the exact amount stated in the group");
        }
        int balance;
        balance = groupMember.getTotalAmountSaved() + updateGroupMemberDto.getAmountToSave();
            List<GroupMember> groupMembers = new ArrayList<>();
            groupMembers.add(groupMember);
            groupMember.setEsusuGroup(findByName);
            groupMembersRepository.save(groupMember);

            findByName.setGroupMembers(groupMembers);
            groupMember.setTotalAmountSaved(balance);
            esusuGroupRepository.save(findByName);


        return groupMember;
    }

    public List<GroupMember> allGroupMembers() {
        return groupMembersRepository.findAll();
    }
}
