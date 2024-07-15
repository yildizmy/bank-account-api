package com.github.yildizmy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyAmountRequest {

    @Min(value = 1, message = "{amount.min}")
    @NotNull(message = "{amount.notNull}")
    private BigDecimal amount;
}
