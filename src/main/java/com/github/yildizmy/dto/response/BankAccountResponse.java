package com.github.yildizmy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BankAccountResponse {
    private UUID id;
    private String owner;
    private BigDecimal balance;
}
