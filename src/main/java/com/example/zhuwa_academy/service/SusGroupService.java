package com.example.zhuwa_academy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.zhuwa_academy.dtos.susGroup.SusGroupResponse;
import com.example.zhuwa_academy.dtos.susGroup.SusGroupdtoRquest;
import com.example.zhuwa_academy.model.SusuGroup;
import com.example.zhuwa_academy.repository.SUsuRepository;

import lombok.NoArgsConstructor;




@Service 
@NoArgsConstructor 

public class SusGroupService {

    private final SUsuRepository susuRepository;

    
public SusGroupService(SUsuRepository susuRepository) {
    this.susuRepository = susuRepository;
}

    private SusGroupResponse  toDTO(SusuGroup group) {
        SusGroupResponse dto = new SusGroupResponse();
        dto.setGroupName(group.getGroupName());
        dto.setMemberNames(group.getMemberNames());
        dto.setMaxMembers(group.getMaxMembers());
        dto.setCurrentWeekNo(group.getCurrentWeekNo());
        return dto;
    }

   private SusuGroup fromDTO(SusGroupdtoRquest request) {
       if (request == null) { return null; } 
       SusuGroup group = new SusuGroup();
       group.setCurrentWeekNo(request.getCurrentWeekNo());
        group.setGroupName(request.getGroupName());
        group.setMaxMembers(request.getMaxMembers());
        group.setMemberNames(request.getMemberNames());
       group.setWeeklyAmount(request.getWeeklyAmount());
     
    return group;
    
    }


    public  SusGroupResponse createGroup(SusGroupdtoRquest request){

         if (request == null) {
        throw new IllegalArgumentException("Request is required");
    }

    if (request.getGroupName() == null ||
            request.getGroupName().isBlank()) {
        throw new IllegalArgumentException("Group name is required");
    }

    if (request.getWeeklyAmount() == null ||
            request.getWeeklyAmount().compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException(
                "Weekly amount must be positive");
    }

    if (request.getMaxMembers() == null ||
            request.getMaxMembers() < 2) {
        throw new IllegalArgumentException(
                "Maximum members must be at least 2");
    }

        SusuGroup group = fromDTO(request);

        SusuGroup saved = susuRepository.save(group);
        return toDTO(saved);

    }

    



}
