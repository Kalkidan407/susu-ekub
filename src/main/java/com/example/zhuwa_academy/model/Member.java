package com.example.zhuwa_academy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class Member {

    @Id 
    private Long id;

    private SusuGroup susuGroup;

    private String name;

    private Integer joinOrder;
    
}
