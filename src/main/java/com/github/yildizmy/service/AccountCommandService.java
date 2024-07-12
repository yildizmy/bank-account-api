package com.github.yildizmy.service;

import com.github.yildizmy.coreapi.commands.CreateAccountCommand;
import com.github.yildizmy.coreapi.commands.CreditMoneyCommand;
import com.github.yildizmy.coreapi.commands.DebitMoneyCommand;
import com.github.yildizmy.dto.request.AccountRequest;
import com.github.yildizmy.dto.request.MoneyAmountRequest;
import com.github.yildizmy.dto.response.CommandResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.github.yildizmy.common.Util.formatUuid;

@Service
@RequiredArgsConstructor
public class AccountCommandService {

    private final CommandGateway commandGateway;

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
                                                                   MoneyAmountRequest request) {
        final UUID accountId = formatUuid(rawAccountId);
        return commandGateway.send(new CreditMoneyCommand(
                accountId,
                request.getAmount()
        )).thenApply(result -> CommandResponse.builder().id(accountId).build());
    }

    public CompletableFuture<CommandResponse> debitMoneyFromAccount(String rawAccountId,
                                                                    MoneyAmountRequest request) {
        final UUID accountId = formatUuid(rawAccountId);
        return commandGateway.send(new DebitMoneyCommand(
                accountId,
                request.getAmount()
        )).thenApply(result -> CommandResponse.builder().id(accountId).build());
    }
}
