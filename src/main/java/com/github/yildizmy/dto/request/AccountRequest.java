package com.github.yildizmy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountRequest {

    @Min(value = 1, message = "{initialBalance.min}")
    @NotNull(message = "{initialBalance.notNull}")
    private BigDecimal initialBalance;

    @NotBlank(message = "{owner.notBlank}")
    private String owner;
}
