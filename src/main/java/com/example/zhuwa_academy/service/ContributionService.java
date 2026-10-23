package com.example.zhuwa_academy.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zhuwa_academy.enums.WeekStatus;
import com.example.zhuwa_academy.model.Contribution;
import com.example.zhuwa_academy.model.Member;
import com.example.zhuwa_academy.model.SusuGroup;
import com.example.zhuwa_academy.model.Week;
import com.example.zhuwa_academy.repository.ContributionRepository;
import com.example.zhuwa_academy.repository.MemberRepository;
import com.example.zhuwa_academy.repository.SUsuRepository;
import com.example.zhuwa_academy.repository.WeekRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class ContributionService {

    private final ContributionRepository contributionRepository;
    private final MemberRepository memberRepository;
    private final SUsuRepository groupRepository;
    private final WeekRepository weekRepository;


    @Transactional
    public Contribution recordContribution(
            Long groupId, Long memberId, BigDecimal amount) {

        SusuGroup group = groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Group not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Member not found"));

        if (!member.getSusuGroup().getId().equals(groupId)) {
            throw new IllegalArgumentException(
                    "Member does not belong to this group");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Contribution must be greater than zero");
        }

        Week week = weekRepository
                .findBySusuGroup_IdAndWeekNumber(
                        groupId, group.getCurrentWeekNo())
                .orElseThrow(() ->
                        new IllegalArgumentException("Current week not found"));

        if (week.getStatus() != WeekStatus.OPEN) {
            throw new IllegalArgumentException("This week is closed");
        }

        BigDecimal alreadyPaid = contributionRepository.sumPayments(
                memberId, week.getId());

        if (alreadyPaid == null) {
            alreadyPaid = BigDecimal.ZERO;
        }

        BigDecimal remaining = group.getWeeklyAmount().subtract(alreadyPaid);

        if (amount.compareTo(remaining) > 0) {
            throw new IllegalArgumentException(
                    "Overpayment rejected. Remaining amount: " + remaining);
        }

        return contributionRepository.save(
                new Contribution(member, week, amount));
    }
}