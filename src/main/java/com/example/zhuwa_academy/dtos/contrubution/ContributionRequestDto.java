package com.example.zhuwa_academy.dtos.contrubution;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContributionRequestDto {

    @NotNull
    private Long memberId;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal amount;
}