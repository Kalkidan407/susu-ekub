package com.example.zhuwa_academy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zhuwa_academy.model.Member;
import com.example.zhuwa_academy.model.SusuGroup;
import com.example.zhuwa_academy.model.User;
import com.example.zhuwa_academy.repository.MemberRepository;
import com.example.zhuwa_academy.repository.SUsuRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final SUsuRepository groupRepository;

    public MemberService(
            MemberRepository memberRepository,
            SUsuRepository groupRepository) {
        this.memberRepository = memberRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional
    public Member addMember(Long groupId, User name) {
        if (name == null ) {
            throw new IllegalArgumentException("Member name is required");
        }

        SusuGroup group = groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Group not found"));

        long memberCount = memberRepository.countBySusuGroup_Id(groupId);

        if (memberCount >= group.getMaxMembers()) {
            throw new IllegalArgumentException("Group is full");
        }

        if (memberRepository.existsBySusuGroup_IdAndNameIgnoreCase(
                groupId, name)) {
            throw new IllegalArgumentException(
                    "Member name already exists in this group");
        }

        // Membership is fixed once the first week has started.
        if (group.getCurrentWeekNo() > 1) {
            throw new IllegalArgumentException(
                    "Cannot add members after the first week closes");
        }

        Member member = new Member(
                name,
                group,
                Math.toIntExact(memberCount + 1));

        return memberRepository.save(member);
    }
}