package com.example.zhuwa_academy.controller;

import com.example.zhuwa_academy.service.ContributionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/groups/{groupId}/contributions")
public class ContributionController {

    private final ContributionService contributionService;

    public ContributionController(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object recordContribution(
            @PathVariable Long groupId,
            @RequestParam Long memberId,
            @RequestParam BigDecimal amount) {

        return contributionService.recordContribution(
                groupId, memberId, amount
        );
    }
}