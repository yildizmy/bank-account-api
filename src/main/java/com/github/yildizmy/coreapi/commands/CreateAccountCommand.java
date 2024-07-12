package com.github.yildizmy.coreapi.commands;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@RequiredArgsConstructor
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private UUID accountId;
    private String owner;
    private BigDecimal initialBalance;
}
