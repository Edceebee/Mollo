package com.Mollo.assessment.controllers;

import com.Mollo.assessment.entities.EsusuGroup;
import com.Mollo.assessment.entities.GroupAdmin;
import com.Mollo.assessment.entities.GroupMember;
import com.Mollo.assessment.entities.dtos.GroupAdminDto;
import com.Mollo.assessment.entities.dtos.GroupMemberDto;
import com.Mollo.assessment.responses.ApiResponses;
import com.Mollo.assessment.service.GroupAdminService;
import com.Mollo.assessment.service.GroupMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groupAdmins")
public class GroupAdminController {

    @Autowired
    GroupAdminService groupAdminService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<?> registerGroupAdmin(@RequestBody GroupAdminDto groupAdminDto) {
        try {
            GroupAdmin groupAdminRequest = modelMapper.map(groupAdminDto, GroupAdmin.class);
            GroupAdmin groupAdmin = groupAdminService.registerAdmin(groupAdminRequest);

            GroupAdminDto groupAdminResponse = modelMapper.map(groupAdmin, GroupAdminDto.class);
            return new ResponseEntity<>(groupAdminResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponses(exception.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/")
    public List<GroupAdmin> getAllGroups() {
        return groupAdminService.getAllGroupAdmins();
    }

    @GetMapping("/{groupAdminId}/{nameOfGroup}")
    public List<GroupMember> getMembersFromGroup(@PathVariable Long groupAdminId, @PathVariable String nameOfGroup) {
//        try {
            return groupAdminService.getGroupMembers(groupAdminId, nameOfGroup);
//            return new ResponseEntity<>(groupMember, HttpStatus.OK);
//
//        } catch (IllegalArgumentException exception) {
//            return new ResponseEntity<>(new ApiResponses(exception.getMessage()),
//                    HttpStatus.BAD_REQUEST);
//        }
    }
}
