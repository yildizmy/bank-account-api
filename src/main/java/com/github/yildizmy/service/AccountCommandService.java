package com.github.yildizmy.service;

import com.github.yildizmy.coreapi.commands.CreateAccountCommand;
import com.github.yildizmy.coreapi.commands.CreditMoneyCommand;
import com.github.yildizmy.coreapi.commands.DebitMoneyCommand;
import com.github.yildizmy.dto.request.AccountRequest;
import com.github.yildizmy.dto.request.MoneyAmountRequest;
import com.github.yildizmy.dto.response.CommandResponse;
import com.github.yildizmy.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.github.yildizmy.common.Constants.ACCOUNT_NOT_FOUND;
import static com.github.yildizmy.common.Util.formatUuid;

@Service
@RequiredArgsConstructor
public class AccountCommandService {

    private final CommandGateway commandGateway;
    private final BankAccountRepository repository;

    @Async
    public CompletableFuture<CommandResponse> createAccount(AccountRequest request) {
        final UUID accountId = UUID.randomUUID();
        return commandGateway.send(new CreateAccountCommand(
                accountId,
                request.getOwner(),
                request.getInitialBalance()
        )).thenApply(result -> CommandResponse.builder().id(accountId).build());
    }

    public CompletableFuture<CommandResponse> creditMoneyToAccount(String rawAccountId,
                                                                   MoneyAmountRequest request) throws AccountNotFoundException {
        final UUID accountId = formatUuid(rawAccountId);
        if(!repository.existsBankAccountById(accountId))
            throw new AccountNotFoundException(MessageFormat.format(ACCOUNT_NOT_FOUND, accountId));

        return commandGateway.send(new CreditMoneyCommand(
                accountId,
                request.getAmount()
        )).thenApply(result -> CommandResponse.builder().id(accountId).build());
    }

    public CompletableFuture<CommandResponse> debitMoneyFromAccount(String rawAccountId,
                                                                    MoneyAmountRequest request) throws AccountNotFoundException {
        final UUID accountId = formatUuid(rawAccountId);
        if(!repository.existsBankAccountById(accountId))
            throw new AccountNotFoundException(MessageFormat.format(ACCOUNT_NOT_FOUND, accountId));

        return commandGateway.send(new DebitMoneyCommand(
                accountId,
                request.getAmount()
        )).thenApply(result -> CommandResponse.builder().id(accountId).build());
    }
}
