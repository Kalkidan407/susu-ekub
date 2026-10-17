package com.example.zhuwa_academy.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.zhuwa_academy.model.*;
import com.example.zhuwa_academy.repository.*;

@RestController
@RequestMapping("/api/groups/{groupId}/report")
public class ReportController {

    private final SUsuRepository groupRepository;
    private final MemberRepository memberRepository;
    private final WeekRepository weekRepository;
    private final ContributionRepository contributionRepository;
    private final PayoutRepository payoutRepository;

    public ReportController(
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

    @GetMapping
    public Map<String, Object> getReport(@PathVariable Long groupId) {
        SusuGroup group = groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Group not found"));

        Week week = weekRepository
                .findBySusuGroup_IdAndWeekNumber(
                        groupId, group.getCurrentWeekNo())
                .orElseThrow(() ->
                        new IllegalArgumentException("Current week not found"));

        List<Member> members =
                memberRepository.findBySusuGroup_IdOrderByJoinOrderAsc(groupId);

        List<String> paidInFull = new ArrayList<>();
        List<Map<String, Object>> outstanding = new ArrayList<>();

        for (Member member : members) {
            BigDecimal paid = contributionRepository.sumPayments(
                    member.getId(), week.getId());

            if (paid == null) {
                paid = BigDecimal.ZERO;
            }

            BigDecimal owed = group.getWeeklyAmount().subtract(paid);

            if (owed.signum() == 0) {
                paidInFull.add(member.getName());
            } else {
                Map<String, Object> debt = new LinkedHashMap<>();
                debt.put("member", member.getName());
                debt.put("paid", paid);
                debt.put("owed", owed);
                outstanding.add(debt);
            }
        }

        List<Map<String, Object>> history = payoutRepository
                .findByWeek_SusuGroup_IdOrderByWeek_WeekNumberAsc(groupId)
                .stream()
                .map(payout -> {
                    Map<String, Object> entry = new LinkedHashMap<>();
                    entry.put("weekNumber",
                            payout.getWeek().getWeekNumber());
                    entry.put("recipient",
                            payout.getRecipient().getName());
                    entry.put("amount", payout.getAmount());
                    return entry;
                })
                .toList();

        Map<String, Object> report = new LinkedHashMap<>();
        report.put("groupName", group.getGroupName());
        report.put("currentWeek", group.getCurrentWeekNo());
        report.put("paidInFull", paidInFull);
        report.put("outstanding", outstanding);
        report.put("payoutHistory", history);

        return report;
    }
}