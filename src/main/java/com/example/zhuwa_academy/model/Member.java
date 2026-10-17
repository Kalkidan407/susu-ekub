package com.example.zhuwa_academy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "susu_group_id", nullable = false)
    private SusuGroup susuGroup;

    @Column(nullable = false)
    private Integer joinOrder;
}