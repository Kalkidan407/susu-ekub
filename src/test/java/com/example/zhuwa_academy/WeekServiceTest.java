package com.example.zhuwa_academy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.zhuwa_academy.enums.WeekStatus;
import com.example.zhuwa_academy.model.*;
import com.example.zhuwa_academy.repository.*;
import com.example.zhuwa_academy.service.WeekService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WeekServiceTest {

    @Mock SUsuRepository groupRepository;
    @Mock MemberRepository memberRepository;
    @Mock WeekRepository weekRepository;
    @Mock ContributionRepository contributionRepository;
    @Mock PayoutRepository payoutRepository;

    private WeekService service;
    private SusuGroup group;
    private Member first;
    private Member second;
    private Week week;

    @BeforeEach
    void setUp() {
        service = new WeekService(
                groupRepository,
                memberRepository,
                weekRepository,
                contributionRepository,
                payoutRepository);

        group = new SusuGroup();
        group.setId(1L);
        group.setWeeklyAmount(new BigDecimal("100.00"));
        group.setCurrentWeekNo(1);

        first = new Member("Abebe", group, 1);
        first.setId(1L);

        second = new Member("Almaz", group, 2);
        second.setId(2L);

        week = new Week(group, 1);
        week.setId(1L);
        week.setStatus(WeekStatus.OPEN);

        when(groupRepository.findById(1L))
                .thenReturn(Optional.of(group));
        when(weekRepository.findBySusuGroup_IdAndWeekNumber(1L, 1))
                .thenReturn(Optional.of(week));
        when(memberRepository.findBySusuGroup_IdOrderByJoinOrderAsc(1L))
                .thenReturn(List.of(first, second));
    }

    @Test
    void shouldNotCloseWeekWhenMemberOwesMoney() {
        when(contributionRepository.sumPayments(1L, 1L))
                .thenReturn(new BigDecimal("100.00"));
        when(contributionRepository.sumPayments(2L, 1L))
                .thenReturn(new BigDecimal("60.00"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.closeWeek(1L));

        assertTrue(exception.getMessage().contains("Almaz"));
        assertEquals(WeekStatus.OPEN, week.getStatus());

        verify(payoutRepository, never()).save(any(Payout.class));
    }

    @Test
    void shouldCloseWeekAndPayFirstMember() {
        when(contributionRepository.sumPayments(1L, 1L))
                .thenReturn(new BigDecimal("100.00"));
        when(contributionRepository.sumPayments(2L, 1L))
                .thenReturn(new BigDecimal("100.00"));

        when(payoutRepository.save(any(Payout.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payout result = service.closeWeek(1L);

        assertEquals(first, result.getRecipient());
        assertEquals(new BigDecimal("200.00"), result.getAmount());
        assertEquals(WeekStatus.CLOSED, week.getStatus());
        assertEquals(2, group.getCurrentWeekNo());

        verify(weekRepository).save(
                argThat(savedWeek -> savedWeek.getWeekNumber() == 2));
    }

    @Test
    void shouldRejectClosingWeekWithNoMembers() {
        when(memberRepository.findBySusuGroup_IdOrderByJoinOrderAsc(1L))
                .thenReturn(List.of());

        assertThrows(IllegalArgumentException.class,
                () -> service.closeWeek(1L));

        verify(payoutRepository, never()).save(any(Payout.class));
    }
}