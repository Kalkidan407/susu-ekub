package com.example.zhuwa_academy.dtos.MemberDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    private Long id;
    private String name;
    private Integer joinOrder;
}