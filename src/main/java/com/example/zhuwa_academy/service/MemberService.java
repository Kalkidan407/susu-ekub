package com.example.zhuwa_academy.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zhuwa_academy.dtos.MemberDto.MemberRequestDto;
import com.example.zhuwa_academy.dtos.MemberDto.MemberResponseDto;
import com.example.zhuwa_academy.model.Member;
import com.example.zhuwa_academy.model.SusuGroup;
import com.example.zhuwa_academy.repository.MemberRepository;
import com.example.zhuwa_academy.repository.SUsuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SUsuRepository susuRepository;

    @Transactional
    public MemberResponseDto addMember(
            Long groupId, MemberRequestDto request) {

        SusuGroup group = susuRepository.findById(groupId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Group not found"));

        if (request == null || request.getName() == null
                || request.getName().isBlank()) {
            throw new IllegalArgumentException("Member name is required");
        }

        if (group.getMembers().size() >= group.getMaxMembers()) {
            throw new IllegalArgumentException("Group is full");
        }

        if (memberRepository.existsBySusuGroupIdAndNameIgnoreCase(
                groupId, request.getName().trim())) {
            throw new IllegalArgumentException("Member already exists");
        }

        Member member = new Member();
        member.setName(request.getName().trim());
        member.setSusuGroup(group);
        member.setJoinOrder(group.getMembers().size() + 1);

        Member saved = memberRepository.save(member);

        MemberResponseDto response = new MemberResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setJoinOrder(saved.getJoinOrder());

        return response;
    }
}