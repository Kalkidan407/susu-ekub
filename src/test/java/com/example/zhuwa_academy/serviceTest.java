// package com.example.zhuwa_academy;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import java.math.BigDecimal;
// import java.util.Optional;

// import com.example.zhuwa_academy.enums.WeekStatus;
// import com.example.zhuwa_academy.model.*;
// import com.example.zhuwa_academy.repository.*;
// import com.example.zhuwa_academy.service.ContributionService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith(MockitoExtension.class)
// class ContributionServiceTest {

//     @Mock ContributionRepository contributionRepository;
//     @Mock MemberRepository memberRepository;
//     @Mock SUsuRepository groupRepository;
//     @Mock WeekRepository weekRepository;

//     private ContributionService service;
//     private SusuGroup group;
//     private Member member;
//     private Week week;

//     @BeforeEach
//     void setUp() {
//         service = new ContributionService(
//                 contributionRepository,
//                 memberRepository,
//                 groupRepository,
//                 weekRepository);

//         group = new SusuGroup();
//         group.setId(1L);
//         group.setWeeklyAmount(new BigDecimal("100.00"));
//         group.setCurrentWeekNo(1);

//         member = new Member(User, group, 1);
//         member.setId(1L);

//         week = new Week(group, 1);
//         week.setId(1L);
//         week.setStatus(WeekStatus.OPEN);

//         when(groupRepository.findById(1L))
//                 .thenReturn(Optional.of(group));
//         when(memberRepository.findById(1L))
//                 .thenReturn(Optional.of(member));
//         when(weekRepository.findBySusuGroup_IdAndWeekNumber(1L, 1))
//                 .thenReturn(Optional.of(week));
//     }

//     @Test
//     void shouldAcceptPartialContribution() {
//         when(contributionRepository.sumPayments(1L, 1L))
//                 .thenReturn(new BigDecimal("40.00"));

//         when(contributionRepository.save(any(Contribution.class)))
//                 .thenAnswer(invocation -> invocation.getArgument(0));

//         Contribution result = service.recordContribution(
//                 1L, 1L, new BigDecimal("30.00"));

//         assertEquals(new BigDecimal("30.00"), result.getAmount());
//         verify(contributionRepository).save(any(Contribution.class));
//     }

//     @Test
//     void shouldRejectOverpayment() {
//         when(contributionRepository.sumPayments(1L, 1L))
//                 .thenReturn(new BigDecimal("40.00"));

//         assertThrows(IllegalArgumentException.class, () ->
//                 service.recordContribution(
//                         1L, 1L, new BigDecimal("61.00")));

//         verify(contributionRepository, never())
//                 .save(any(Contribution.class));
//     }

//     @Test
//     void shouldRejectZeroContribution() {
//         assertThrows(IllegalArgumentException.class, () ->
//                 service.recordContribution(
//                         1L, 1L, BigDecimal.ZERO));

//         verify(contributionRepository, never())
//                 .save(any(Contribution.class));
//     }
// }