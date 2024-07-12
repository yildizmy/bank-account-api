package com.github.yildizmy.coreapi.projection;

import com.github.yildizmy.coreapi.events.AccountCreatedEvent;
import com.github.yildizmy.coreapi.events.MoneyCreditedEvent;
import com.github.yildizmy.coreapi.events.MoneyDebitedEvent;
import com.github.yildizmy.coreapi.queries.FindBankAccountQuery;
import com.github.yildizmy.entity.BankAccount;
import com.github.yildizmy.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.text.MessageFormat;

import static com.github.yildizmy.common.Constants.*;

@Slf4j(topic = "BankAccountProjection")
@RequiredArgsConstructor
@Service
public class BankAccountProjection {

    private final BankAccountRepository repository;
    private final QueryUpdateEmitter updateEmitter;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        log.info(HANDLING_ACCOUNT_CREATION_COMMAND, event.getId());
        BankAccount bankAccount = new BankAccount(
                event.getId(),
                event.getOwner(),
                event.getInitialBalance()
        );
        repository.save(bankAccount);
    }

    @EventHandler
    public void on(MoneyCreditedEvent event) throws AccountNotFoundException {
        log.info(HANDLING_ACCOUNT_CREDIT_COMMAND, event.getId());
        final BankAccount bankAccount = repository.findById(event.getId())
                .orElseThrow(() -> new AccountNotFoundException(MessageFormat.format(CANNOT_FOUND_ACCOUNT_NUMBER, event.getId().toString())));
        bankAccount.setBalance(bankAccount.getBalance().add(event.getCreditAmount()));
        repository.save(bankAccount);
    }

    @EventHandler
    public void on(MoneyDebitedEvent event) throws AccountNotFoundException {
        log.info(HANDLING_ACCOUNT_DEBIT_COMMAND, event.getId());
        final BankAccount bankAccount = repository.findById(event.getId())
                .orElseThrow(() -> new AccountNotFoundException(MessageFormat.format(CANNOT_FOUND_ACCOUNT_NUMBER, event.getId().toString())));
        bankAccount.setBalance(bankAccount.getBalance().subtract(event.getDebitAmount()));
        repository.save(bankAccount);
    }

    @QueryHandler
    public BankAccount handle(FindBankAccountQuery query) {
        log.info(HANDLING_FIND_BANK_ACCOUNT_QUERY, query);
        return repository.findById(query.getAccountId()).orElse(null);
    }
}
