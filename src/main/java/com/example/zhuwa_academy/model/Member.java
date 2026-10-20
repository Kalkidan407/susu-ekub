package com.example.zhuwa_academy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    name = "members",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"susu_group_id", "name"}
    )
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private User name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "susu_group_id", nullable = false)
    private SusuGroup susuGroup;

    @Column(nullable = false)
    private Integer joinOrder;

    public Member() {
    }

    public Member(User name, SusuGroup susuGroup, Integer joinOrder) {
        this.name = name;
        this.susuGroup = susuGroup;
        this.joinOrder = joinOrder;
    }
}