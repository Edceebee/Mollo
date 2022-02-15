package com.Mollo.assessment.controllers;

import com.Mollo.assessment.entities.GroupMember;
import com.Mollo.assessment.entities.dtos.GroupMemberDto;
import com.Mollo.assessment.entities.dtos.UpdateGroupMemberDto;
import com.Mollo.assessment.responses.ApiResponses;
import com.Mollo.assessment.service.GroupMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/groupMembers")
@RestController
public class GroupMemberController {

    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> registerGroupMember(@RequestBody GroupMemberDto groupMemberDto) {
        try {
            GroupMember groupMemberRequest = modelMapper.map(groupMemberDto, GroupMember.class);
            GroupMember groupMember = groupMemberService.registerGroupMember(groupMemberRequest);

            GroupMemberDto groupMemberResponse = modelMapper.map(groupMember, GroupMemberDto.class);
            return new ResponseEntity<>(groupMemberResponse, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponses(exception.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/{groupMemberId}")
    public ResponseEntity<?> updateMember(@PathVariable Long groupMemberId, @RequestBody
                                                 UpdateGroupMemberDto updateGroupMemberDto) {
        try {
            GroupMember updateGroupMember = groupMemberService.updateGroupMember(groupMemberId, updateGroupMemberDto);

            return new ResponseEntity<>(updateGroupMember, HttpStatus.OK);
        }
        catch (IllegalArgumentException message) {
            return new ResponseEntity<>(new ApiResponses(message.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
