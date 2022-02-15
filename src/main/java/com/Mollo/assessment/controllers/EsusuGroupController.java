package com.Mollo.assessment.controllers;

import com.Mollo.assessment.entities.EsusuGroup;
import com.Mollo.assessment.entities.GroupAdmin;
import com.Mollo.assessment.entities.dtos.EsusuGroupDto;
import com.Mollo.assessment.entities.dtos.GroupAdminDto;
import com.Mollo.assessment.responses.ApiResponses;
import com.Mollo.assessment.service.EsusuGroupService;
import com.Mollo.assessment.service.GroupAdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/esusuGroup")
@RestController
public class EsusuGroupController {

    @Autowired
    EsusuGroupService esusuGroupService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/{adminId}")
    public ResponseEntity<?> createEsusuGroup(@PathVariable Long adminId,
                                              @RequestBody EsusuGroupDto esusuGroupDto) {
        try {
            EsusuGroup esusuGroupRequest = modelMapper.map(esusuGroupDto, EsusuGroup.class);
            EsusuGroup esusuGroup = esusuGroupService.createEsusuGroup(adminId, esusuGroupRequest);

            EsusuGroupDto esusuGroupResponse = modelMapper.map(esusuGroup, EsusuGroupDto.class);
            return new ResponseEntity<>(esusuGroupResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponses(exception.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/")
    public List<EsusuGroup> getAllGroups() {
        return esusuGroupService.getAllGroups();
    }
}
