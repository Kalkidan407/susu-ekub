package com.example.zhuwa_academy.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.zhuwa_academy.model.Payout;
import com.example.zhuwa_academy.service.WeekService;

@RestController
@RequestMapping("/api/groups/{groupId}")
public class WeekController {

    private final WeekService service;

    public WeekController(WeekService service) {
        this.service = service;
    }

    @PostMapping("/close-week")
    public Map<String, Object> closeWeek(
            @PathVariable Long groupId) {

        Payout payout = service.closeWeek(groupId);

        return Map.of(
                "weekNumber", payout.getWeek().getWeekNumber(),
                "recipient", payout.getRecipient().getName(),
                "amount", payout.getAmount(),
                "status", "CLOSED");
    }
}