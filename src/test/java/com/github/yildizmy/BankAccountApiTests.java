package com.github.yildizmy;

import com.github.yildizmy.coreapi.aggregate.BankAccountAggregate;
import com.github.yildizmy.coreapi.commands.CreateAccountCommand;
import com.github.yildizmy.coreapi.commands.CreditMoneyCommand;
import com.github.yildizmy.coreapi.commands.DebitMoneyCommand;
import com.github.yildizmy.coreapi.events.AccountCreatedEvent;
import com.github.yildizmy.coreapi.events.MoneyCreditedEvent;
import com.github.yildizmy.coreapi.events.MoneyDebitedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

class BankAccountApiTests {

    private static final String customerName = "John Doe";
    private UUID accountId;
    private AggregateTestFixture<BankAccountAggregate> fixture;

    @BeforeEach
    public void setUp() {
        accountId = UUID.randomUUID();
        fixture = new AggregateTestFixture<>(BankAccountAggregate.class);
    }

    @Test
    public void should_dispatchAccountCreatedEvent_when_CreateAccountCommandIsExecuted() {
        BigDecimal initialBalance = BigDecimal.valueOf(1000);

        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand(accountId, customerName, initialBalance))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AccountCreatedEvent(accountId, customerName, initialBalance));
    }

    @Test
    public void should_dispatchMoneyCreditedEvent_when_CreditMoneyCommandIsExecuted() {
        BigDecimal initialBalance = BigDecimal.valueOf(1000);
        BigDecimal creditAmount = BigDecimal.valueOf(500);

        fixture.given(new AccountCreatedEvent(accountId, customerName, initialBalance))
                .when(new CreditMoneyCommand(accountId, creditAmount))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new MoneyCreditedEvent(accountId, creditAmount));
    }

    @Test
    public void should_dispatchMoneyDebitedEvent_when_DebitMoneyCommandIsExecuted() {
        BigDecimal initialBalance = BigDecimal.valueOf(1000);
        BigDecimal debitAmount = BigDecimal.valueOf(500);

        fixture.given(new AccountCreatedEvent(accountId, customerName, initialBalance))
                .when(new DebitMoneyCommand(accountId, debitAmount))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new MoneyDebitedEvent(accountId, debitAmount));
    }

    @Test
    public void should_not_dispatchMoneyDebitedEvent_when_BalanceIsLowerThanDebitAmount() {
        BigDecimal initialBalance = BigDecimal.valueOf(1000);
        BigDecimal debitAmount = BigDecimal.valueOf(1500);

        fixture.given(new AccountCreatedEvent(accountId, customerName, initialBalance))
                .when(new DebitMoneyCommand(accountId, debitAmount))
                .expectNoEvents();
    }
}
