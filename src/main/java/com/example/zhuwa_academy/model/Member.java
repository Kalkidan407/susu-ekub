package com.example.zhuwa_academy.model;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;



@Entity 
@Setter 
@Getter 
public class Member {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn (name = "sus_group_id")
    private SusuGroup susuGroup;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private Integer joinOrder;

    @OneToMany 
    @JoinColumn (name = "contribution_id")
    private List<Contribution> contributions;
    
    @Column (nullable = false)
    private Contribution contribution;


    @OneToMany 
    @JoinColumn (name = "payout_id")
    private List<Payout> payouts;
    

    

    
}
