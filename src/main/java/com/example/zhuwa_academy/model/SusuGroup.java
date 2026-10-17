package com.example.zhuwa_academy.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class SusuGroup {

    @Id 
    private Long id;

    private String groupName;

    private Integer maxMembers;

    private BigDecimal weeklyAmount;
    
    private Integer currentWeekNo;

    
}
