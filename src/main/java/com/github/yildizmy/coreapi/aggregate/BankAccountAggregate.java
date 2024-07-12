package com.github.yildizmy.coreapi.aggregate;

import com.github.yildizmy.coreapi.commands.CreateAccountCommand;
import com.github.yildizmy.coreapi.commands.CreditMoneyCommand;
import com.github.yildizmy.coreapi.commands.DebitMoneyCommand;
import com.github.yildizmy.coreapi.events.AccountCreatedEvent;
import com.github.yildizmy.coreapi.events.MoneyCreditedEvent;
import com.github.yildizmy.coreapi.events.MoneyDebitedEvent;
import com.github.yildizmy.exception.InsufficientBalanceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.UUID;

import static com.github.yildizmy.common.Constants.INSUFFICIENT_BALANCE;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Aggregate
public class BankAccountAggregate {

    @AggregateIdentifier
    private UUID id;
    private String owner;
    private BigDecimal balance;

    @CommandHandler
    public BankAccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        command.getAccountId(),
                        command.getOwner(),
                        command.getInitialBalance()
                )
        );
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        id = event.getId();
        owner = event.getOwner();
        balance = event.getInitialBalance();
    }

    @CommandHandler
    public void handle(CreditMoneyCommand command) {
        AggregateLifecycle.apply(
                new MoneyCreditedEvent(
                        command.getAccountId(),
                        command.getCreditAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent event) {
        balance = balance.add(event.getCreditAmount());
    }

    @CommandHandler
    public void handle(DebitMoneyCommand command) {
        AggregateLifecycle.apply(
                new MoneyDebitedEvent(
                        command.getAccountId(),
                        command.getDebitAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyDebitedEvent event) throws InsufficientBalanceException {
        if (balance.compareTo(event.getDebitAmount()) < 0) {
            throw new InsufficientBalanceException(MessageFormat.format(INSUFFICIENT_BALANCE, event.getId(), event.getDebitAmount()));
        }
        balance = balance.subtract(event.getDebitAmount());
    }
}
