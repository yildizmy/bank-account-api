package com.github.yildizmy.rest;

import com.github.yildizmy.dto.request.AccountRequest;
import com.github.yildizmy.dto.request.MoneyAmountRequest;
import com.github.yildizmy.dto.response.ApiResponse;
import com.github.yildizmy.dto.response.CommandResponse;
import com.github.yildizmy.service.AccountCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.time.Clock;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.github.yildizmy.common.Constants.SUCCESS;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountCommandController {

    private final Clock clock;
    private final AccountCommandService accountCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<CommandResponse>> createAccount(@Valid @RequestBody AccountRequest request)
            throws ExecutionException, InterruptedException {
        final CompletableFuture<CommandResponse> response = accountCommandService.createAccount(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response.get()));
    }

    @PutMapping(value = "/credit/{accountId}")
    public ResponseEntity<ApiResponse<CommandResponse>> creditMoneyToAccount(@PathVariable(value = "accountId") String accountId,
                                                                             @Valid @RequestBody MoneyAmountRequest request)
            throws ExecutionException, InterruptedException, AccountNotFoundException {
        final CompletableFuture<CommandResponse> response = accountCommandService.creditMoneyToAccount(accountId, request);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response.get()));
    }

    @PutMapping("/debit/{accountId}")
    public ResponseEntity<ApiResponse<CommandResponse>> debitMoneyFromAccount(@PathVariable(value = "accountId") String accountId,
                                                                              @Valid @RequestBody MoneyAmountRequest request)
            throws ExecutionException, InterruptedException, AccountNotFoundException {
        final CompletableFuture<CommandResponse> response = accountCommandService.debitMoneyFromAccount(accountId, request);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response.get()));
    }
}
