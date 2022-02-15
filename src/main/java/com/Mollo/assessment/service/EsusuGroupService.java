package com.Mollo.assessment.service;

import com.Mollo.assessment.entities.EsusuGroup;
import com.Mollo.assessment.entities.GroupAdmin;
import com.Mollo.assessment.entities.GroupMember;
import com.Mollo.assessment.repositories.EsusuGroupRepository;
import com.Mollo.assessment.repositories.GroupAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EsusuGroupService {

    @Autowired
    EsusuGroupRepository esusuGroupRepository;


    @Autowired
    GroupAdminRepository groupAdminRepository;

    public EsusuGroup createEsusuGroup(Long groupAdminId, EsusuGroup esusuGroup) {

        GroupAdmin groupAdmin = new GroupAdmin();

        Optional<EsusuGroup> findByGroupName = esusuGroupRepository.findByGroupName(esusuGroup.getGroupName());
        if (findByGroupName.isPresent()) {
            throw new IllegalArgumentException("Sorry, group name with : " + esusuGroup.getGroupName() + " already exist");
        }
        Optional<GroupAdmin> findByAdminId = groupAdminRepository.findById(groupAdminId);
        if (findByAdminId.isPresent()) {

            groupAdmin.setEsusuGroup(esusuGroup);
            esusuGroup.setGroupAdminName(findByAdminId.get().getAdminFullName());
            esusuGroupRepository.save(esusuGroup);
            groupAdminRepository.save(groupAdmin);


            return esusuGroup;
        }
        throw new IllegalArgumentException("Sorry, Admin with id : " + groupAdminId + " does not exist");


    }

    public List<EsusuGroup>  getAllGroups() {
        return esusuGroupRepository.findAll();
    }
}
