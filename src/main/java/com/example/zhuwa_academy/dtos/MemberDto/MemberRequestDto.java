package com.example.zhuwa_academy.dtos.MemberDto;

import com.example.zhuwa_academy.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

    @NotBlank
    @Size(max = 100)
    private User name;
}