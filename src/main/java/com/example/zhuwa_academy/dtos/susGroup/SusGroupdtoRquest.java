package com.example.zhuwa_academy.dtos.susGroup;

import java.math.BigDecimal;
import java.util.List;

import com.example.zhuwa_academy.model.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@NoArgsConstructor
 
public class SusGroupdtoRquest {
    
    private String groupName;
    private Integer maxMembers;
    private BigDecimal weeklyAmount;
    private Integer currentWeekNo;
    private List<Member> memberNames;
    
}
