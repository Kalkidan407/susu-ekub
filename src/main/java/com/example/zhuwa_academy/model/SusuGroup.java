package com.example.zhuwa_academy.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "susu_groups")
public class SusuGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groupName;

    @Column(nullable = false)
    private Integer maxMembers;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal weeklyAmount;

    @Column(nullable = false)
    private Integer currentWeekNo = 1;

    @OneToMany(mappedBy = "susuGroup")
    private List<Member> members = new ArrayList<>();
}