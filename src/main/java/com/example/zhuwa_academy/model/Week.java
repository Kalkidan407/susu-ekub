package com.example.zhuwa_academy.model;


import com.example.zhuwa_academy.enums.WeekStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Setter 
@Getter 
public class Week {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SusuGroup susGroup;

    private Integer weekNumber;

    @Enumerated(EnumType.STRING)
    private WeekStatus status;

    @OneToOne 
    @JoinColumn (name = "payout_id")
    private Payout payout;

    @OneToMany 
    @JoinColumn(name = "contribution_id")
    private Contribution contribution;
    
}
