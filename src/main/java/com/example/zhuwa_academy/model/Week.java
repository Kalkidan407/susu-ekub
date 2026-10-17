package com.example.zhuwa_academy.model;

import com.example.zhuwa_academy.enums.WeekStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    name = "weeks",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"susu_group_id", "week_number"}
    )
)
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "susu_group_id", nullable = false)
    private SusuGroup susuGroup;

    @Column(name = "week_number", nullable = false)
    private Integer weekNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WeekStatus status = WeekStatus.OPEN;

    public Week() {
    }

    public Week(SusuGroup susuGroup, Integer weekNumber) {
        this.susuGroup = susuGroup;
        this.weekNumber = weekNumber;
        this.status = WeekStatus.OPEN;
    }
}