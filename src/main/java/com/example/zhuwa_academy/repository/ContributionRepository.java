package com.example.zhuwa_academy.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.zhuwa_academy.model.Contribution;

public interface ContributionRepository
        extends JpaRepository<Contribution, Long> {

    @Query("""
        SELECT COALESCE(SUM(c.amount), 0)
        FROM Contribution c
        WHERE c.member.id = :memberId
          AND c.week.id = :weekId
        """)
    BigDecimal sumMemberPaymentsForWeek(
            @Param("memberId") Long memberId,
            @Param("weekId") Long weekId);

    List<Contribution> findByWeekId(Long weekId);
}