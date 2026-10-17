package com.example.zhuwa_academy.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity 
@Setter 
@Getter 

public class SusuGroup {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groupName;
     
@OneToMany (mappedBy = "susuGroup")
private List<Member> members = new ArrayList<>();

@ManyToOne 
private Week week;

    @Column (nullable = false)
    private Integer maxMembers;
    
    @Column(nullable = false)
    private BigDecimal weeklyAmount;
    
    @Column(nullable = false)
    private Integer currentWeekNo;

    
}
