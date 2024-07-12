package com.github.yildizmy.coreapi.events;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AccountCreatedEvent {
    private UUID id;
    private String owner;
    private BigDecimal initialBalance;
}
