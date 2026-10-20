package com.example.zhuwa_academy.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.zhuwa_academy.model.Week;

public interface WeekRepository extends JpaRepository<Week, Long> {
    Optional<Week> findBySusuGroup_IdAndWeekNumber(
        Long groupId, Integer weekNumber
    );
}