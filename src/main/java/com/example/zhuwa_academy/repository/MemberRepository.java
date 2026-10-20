package com.example.zhuwa_academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zhuwa_academy.model.Member;
import com.example.zhuwa_academy.model.User;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsBySusuGroup_IdAndNameIgnoreCase(
            Long groupId, User name);

    List<Member> findBySusuGroup_IdOrderByJoinOrderAsc(
            Long groupId);

    long countBySusuGroup_Id(Long groupId);
}