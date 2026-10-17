package com.example.zhuwa_academy.model;


import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class Payout {

    @Id 
    private Long id;
    
    private Member member; 

    private Integer weekNumber;

    private BigDecimal amount;

    
}
