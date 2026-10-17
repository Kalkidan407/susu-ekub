package com.example.zhuwa_academy.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity 
public class Contribution {

    private Long id;

    private Member member;

    private Integer weekNumber;

    private BigDecimal amount;


    
}
