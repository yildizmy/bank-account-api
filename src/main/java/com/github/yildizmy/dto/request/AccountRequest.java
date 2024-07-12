package com.github.yildizmy.dto.request;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountRequest {
    private BigDecimal initialBalance;
    private String owner;
}
