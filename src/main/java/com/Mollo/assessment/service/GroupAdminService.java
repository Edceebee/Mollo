package com.Mollo.assessment.service;

import com.Mollo.assessment.entities.EsusuGroup;
import com.Mollo.assessment.entities.GroupAdmin;
import com.Mollo.assessment.entities.GroupMember;
import com.Mollo.assessment.repositories.EsusuGroupRepository;
import com.Mollo.assessment.repositories.GroupAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class GroupAdminService {


    @Autowired
    GroupAdminRepository groupAdminRepository;

    @Autowired
    EsusuGroupRepository esusuGroupRepository;

    public GroupAdmin registerAdmin(GroupAdmin groupAdmin) {
        EsusuGroup esusuGroup = new EsusuGroup();
        Optional<GroupAdmin> findByGroupAdminName = groupAdminRepository.findByAdminFullName(groupAdmin.getAdminFullName());
        if (findByGroupAdminName.isPresent()) {
            throw new IllegalArgumentException("Sorry, Admin with name " + groupAdmin.getAdminFullName() + " already exist");
        }
        esusuGroup.setGroupAdmin(groupAdmin);
        groupAdminRepository.save(groupAdmin);
        esusuGroupRepository.save(esusuGroup);

        groupAdmin.setEsusuGroup(esusuGroup);
        groupAdminRepository.save(groupAdmin);
        return groupAdmin;
    }

    public List<GroupAdmin> getAllGroupAdmins() {
        return groupAdminRepository.findAll();
    }

    public List<GroupMember> getGroupMembers(Long id, String nameOfGroup) {

        Optional<GroupAdmin> findById = groupAdminRepository.findById(id);
        if (!findById.isPresent()) {
            throw new IllegalArgumentException("Sorry, id " + id + " does not exist");
        }
        List<EsusuGroup> getMembers = esusuGroupRepository.findEsusuGroupByGroupName(nameOfGroup);
        if (getMembers.isEmpty()) {
            throw new IllegalArgumentException("Sorry, group " + nameOfGroup + " does not exist");
        }

        return findById.get().getEsusuGroup().getGroupMembers();
    }
}
