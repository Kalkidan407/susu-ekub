package com.example.zhuwa_academy.model;

import java.math.BigDecimal;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;



@Entity 
@Setter
@Getter 
public class Contribution {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Member member;

    private Integer weekNumber;
    
    private BigDecimal amount;

    @ManyToOne 
    private  Week week;


    
}
