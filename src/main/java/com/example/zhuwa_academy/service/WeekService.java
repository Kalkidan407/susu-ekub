package com.example.zhuwa_academy.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zhuwa_academy.enums.WeekStatus;
import com.example.zhuwa_academy.model.Member;
import com.example.zhuwa_academy.model.Payout;
import com.example.zhuwa_academy.model.SusuGroup;
import com.example.zhuwa_academy.model.Week;
import com.example.zhuwa_academy.repository.ContributionRepository;
import com.example.zhuwa_academy.repository.MemberRepository;
import com.example.zhuwa_academy.repository.PayoutRepository;
import com.example.zhuwa_academy.repository.SUsuRepository;
import com.example.zhuwa_academy.repository.WeekRepository;

@Service
public class WeekService {

    private final SUsuRepository groupRepository;
    private final MemberRepository memberRepository;
    private final WeekRepository weekRepository;
    private final ContributionRepository contributionRepository;
    private final PayoutRepository payoutRepository;

    public WeekService(
            SUsuRepository groupRepository,
            MemberRepository memberRepository,
            WeekRepository weekRepository,
            ContributionRepository contributionRepository,
            PayoutRepository payoutRepository) {
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
        this.weekRepository = weekRepository;
        this.contributionRepository = contributionRepository;
        this.payoutRepository = payoutRepository;
    }

    @Transactional
    public Payout closeWeek(Long groupId) {

        SusuGroup group = groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Group not found"));

        Week week = weekRepository
                .findBySusuGroup_IdAndWeekNumber(
                        groupId, group.getCurrentWeekNo())
                .orElseThrow(() ->
                        new IllegalArgumentException("Current week not found"));

        if (week.getStatus() != WeekStatus.OPEN) {
            throw new IllegalArgumentException("This week is already closed");
        }

        List<Member> members =
                memberRepository.findBySusuGroup_IdOrderByJoinOrderAsc(groupId);

        if (members.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot close a week without members");
        }

        for (Member member : members) {
            BigDecimal paid = contributionRepository.sumPayments(
                    member.getId(), week.getId());

            if (paid == null) {
                paid = BigDecimal.ZERO;
            }

            if (paid.compareTo(group.getWeeklyAmount()) < 0) {
                BigDecimal owed = group.getWeeklyAmount().subtract(paid);
                throw new IllegalArgumentException(
                        member.getName() + " still owes " + owed + " GHS");
            }
        }

        // The recipient rotates in join order, starting with member 1.
        int recipientIndex =
                (group.getCurrentWeekNo() - 1) % members.size();

        Member recipient = members.get(recipientIndex);

        BigDecimal pot = group.getWeeklyAmount()
                .multiply(BigDecimal.valueOf(members.size()));

        week.setStatus(WeekStatus.CLOSED);
        weekRepository.save(week);

        Payout payout = payoutRepository.save(
                new Payout(recipient, week, pot));

        group.setCurrentWeekNo(group.getCurrentWeekNo() + 1);
        groupRepository.save(group);

        weekRepository.save(
                new Week(group, group.getCurrentWeekNo()));

        return payout;
    }
}