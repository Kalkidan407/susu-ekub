package com.example.zhuwa_academy.dtos.susGroup;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  

@NoArgsConstructor 
public class SusGroupdtoRquest {

    @NotBlank 
    private String groupName;
    private Integer maxMembers;
    private String weeklyAmount;
    private Integer currentWeekNo;
    private List<String> memberNames;


    
}
