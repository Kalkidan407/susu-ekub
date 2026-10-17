package com.example.zhuwa_academy.dtos.susGroup;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
public class SusGroupResponse {

    private Long id;
    private String groupName;
    private List<String> memberNames;
    private Integer maxMembers;
    private String weeklyAmount;
    private Integer currentWeekNo;
    

}
