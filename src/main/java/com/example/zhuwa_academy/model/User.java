package com.example.zhuwa_academy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.zhuwa_academy.enums.AccountRole;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
public class User {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private AccountRole role ;

    @Column(nullable = false, unique = true)
    private String email;

    @Column (nullable = false)
    private String passwordHash;

    @OneToMany(mappedBy="user")
    private List<Member> memberships = new ArrayList<>();

    
}
