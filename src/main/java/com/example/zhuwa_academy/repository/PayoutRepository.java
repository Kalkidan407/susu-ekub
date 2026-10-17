package com.example.zhuwa_academy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.zhuwa_academy.model.Payout;

public interface PayoutRepository extends JpaRepository<Payout, Long> {

    List<Payout> findByWeek_SusuGroup_IdOrderByWeek_WeekNumberAsc(
        Long groupId
    );
}