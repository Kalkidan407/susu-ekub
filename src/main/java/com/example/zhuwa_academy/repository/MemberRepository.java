package com.example.zhuwa_academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zhuwa_academy.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsBySusuGroupIdAndNameIgnoreCase(Long groupId, String name);

    List<Member> findBySusuGroupIdOrderByJoinOrderAsc(Long groupId);
}