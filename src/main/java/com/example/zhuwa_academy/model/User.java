package com.example.zhuwa_academy.model;

import java.util.UUID;

import com.example.zhuwa_academy.enums.UserRole;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
public class User {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private UUID id;
    
    private String name;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private UserRole role ;
    



}
