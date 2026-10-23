package com.example.zhuwa_academy.dtos.MemberDto;

import com.example.zhuwa_academy.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    private Long id;
    private User name;
    private Integer joinOrder;
}