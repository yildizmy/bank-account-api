package com.github.yildizmy.coreapi.events;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class MoneyDebitedEvent {
    private UUID id;
    private BigDecimal debitAmount;
}
