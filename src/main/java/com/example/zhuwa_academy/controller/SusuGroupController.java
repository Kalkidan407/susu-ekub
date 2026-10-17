package com.example.zhuwa_academy.controller;

import java.math.BigDecimal;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.zhuwa_academy.model.SusuGroup;
import com.example.zhuwa_academy.service.SusGroupService;

@RestController
@RequestMapping("/api/groups")
public class SusuGroupController {

    private final SusGroupService service;

    public SusuGroupController(SusGroupService service) {
        this.service = service;
    }

    public record CreateGroupRequest(
            @NotBlank String groupName,
            @NotNull @Min(2) Integer maxMembers,
            @NotNull @DecimalMin("0.01") BigDecimal weeklyAmount) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createGroup(
            @Valid @RequestBody CreateGroupRequest request) {

        SusuGroup group = service.createGroup(
                request.groupName(),
                request.maxMembers(),
                request.weeklyAmount());

        return Map.of(
                "id", group.getId(),
                "groupName", group.getGroupName(),
                "maxMembers", group.getMaxMembers(),
                "weeklyAmount", group.getWeeklyAmount(),
                "currentWeekNo", group.getCurrentWeekNo());
    }
}