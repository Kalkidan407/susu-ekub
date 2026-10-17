package com.example.zhuwa_academy.model;


import com.example.zhuwa_academy.enums.WeekStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity 
public class week {
    
    @Id 
    private Long id;

    private SusuGroup susGroup;

    private Integer weekNumber;

    @Enumerated(EnumType.STRING)
    private WeekStatus status;
    
}
