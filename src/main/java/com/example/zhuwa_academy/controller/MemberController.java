package com.example.zhuwa_academy.controller;

import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.zhuwa_academy.model.Member;
import com.example.zhuwa_academy.service.MemberService;

@RestController
@RequestMapping("/api/groups/{groupId}/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    public record AddMemberRequest(
            @NotBlank @Size(max = 100) String name) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addMember(
            @PathVariable Long groupId,
            @Valid @RequestBody AddMemberRequest request) {

        Member member = service.addMember(groupId, request.name());

        return Map.of(
                "id", member.getId(),
                "name", member.getName(),
                "joinOrder", member.getJoinOrder(),
                "groupId", groupId);
    }
}