package com.example.zhuwa_academy.dtos.susGroup;

import java.util.List;

import com.example.zhuwa_academy.model.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 

public class SusGroupResponse {

    private Long id;
    private String groupName;
    private List<Member> memberNames;
    private Integer maxMembers;
    private String weeklyAmount;
    private Integer currentWeekNo;
    

}
