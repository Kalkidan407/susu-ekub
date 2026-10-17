package com.example.zhuwa_academy.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    name = "payouts",
    uniqueConstraints = @UniqueConstraint(columnNames = "week_id")
)
public class Payout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member recipient;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "week_id", nullable = false, unique = true)
    private Week week;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    public Payout() {
    }

    public Payout(Member recipient, Week week, BigDecimal amount) {
        this.recipient = recipient;
        this.week = week;
        this.amount = amount;
    }
}