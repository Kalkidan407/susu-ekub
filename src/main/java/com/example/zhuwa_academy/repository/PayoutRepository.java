package com.example.zhuwa_academy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zhuwa_academy.model.Payout;

@Repository 
public interface PayoutRepository extends JpaRepository<Payout, Long> {
    
}
