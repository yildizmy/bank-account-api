package com.github.yildizmy.coreapi.events;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class MoneyCreditedEvent {
    private UUID id;
    private BigDecimal creditAmount;
}
