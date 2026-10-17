package com.example.zhuwa_academy.model;



import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "week_id")
    private Week week;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
}