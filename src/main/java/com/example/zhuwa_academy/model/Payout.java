package com.example.zhuwa_academy.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter 
@Entity 
public class Payout {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Member member; 

    @ManyToOne
    private List<Member> members ;

    @OneToOne 
    private Week week;

    private Integer weekNumber;

    private BigDecimal amount;

    
}
