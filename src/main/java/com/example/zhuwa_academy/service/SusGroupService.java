package com.example.zhuwa_academy.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zhuwa_academy.model.SusuGroup;
import com.example.zhuwa_academy.model.Week;
import com.example.zhuwa_academy.repository.SUsuRepository;
import com.example.zhuwa_academy.repository.WeekRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 

public class SusGroupService {

    private final SUsuRepository groupRepository;
    private final WeekRepository weekRepository;


    @Transactional
    public SusuGroup createGroup(
            String groupName,
            Integer maxMembers,
            BigDecimal weeklyAmount) {

        if (groupName == null || groupName.isBlank()) {
            throw new IllegalArgumentException("Group name is required");
        }

        if (maxMembers == null || maxMembers < 2) {
            throw new IllegalArgumentException(
                    "A group must allow at least 2 members");
        }

        if (weeklyAmount == null
                || weeklyAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Weekly contribution must be positive");
        }

        SusuGroup group = new SusuGroup();
        group.setGroupName(groupName.trim());
        group.setMaxMembers(maxMembers);
        group.setWeeklyAmount(weeklyAmount);
        group.setCurrentWeekNo(1);

        SusuGroup savedGroup = groupRepository.save(group);

        Week firstWeek = new Week(savedGroup, 1);
        weekRepository.save(firstWeek);

        return savedGroup;
    }
}