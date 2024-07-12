package com.github.yildizmy.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyAmountRequest {
    private BigDecimal amount;
}
