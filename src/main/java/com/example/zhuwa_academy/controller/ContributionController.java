package com.example.zhuwa_academy.controller;

import java.math.BigDecimal;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.zhuwa_academy.model.Contribution;
import com.example.zhuwa_academy.service.ContributionService;

@RestController
@RequestMapping("/api/groups/{groupId}/contributions")
public class ContributionController {

    private final ContributionService service;

    public ContributionController(ContributionService service) {
        this.service = service;
    }

    public record ContributionRequest(
            @NotNull Long memberId,
            @NotNull @DecimalMin("0.01") BigDecimal amount) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> recordContribution(
            @PathVariable Long groupId,
            @Valid @RequestBody ContributionRequest request) {

        Contribution contribution = service.recordContribution(
                groupId, request.memberId(), request.amount());

        return Map.of(
                "id", contribution.getId(),
                "memberId", contribution.getMember().getId(),
                "weekNumber", contribution.getWeek().getWeekNumber(),
                "amount", contribution.getAmount());
    }
}