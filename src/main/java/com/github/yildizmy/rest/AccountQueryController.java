package com.github.yildizmy.rest;

import com.github.yildizmy.dto.response.ApiResponse;
import com.github.yildizmy.dto.response.BankAccountResponse;
import com.github.yildizmy.service.AccountQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.github.yildizmy.common.Constants.SUCCESS;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountQueryController {

    private final Clock clock;
    private final AccountQueryService accountQueryService;

    @GetMapping("/{accountId}")
    public ResponseEntity<ApiResponse<BankAccountResponse>> findById(@PathVariable("accountId") String accountId) throws ExecutionException, InterruptedException {
        final BankAccountResponse response = accountQueryService.findById(accountId);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    @GetMapping("/{accountId}/events")
    public ResponseEntity<ApiResponse<List<Object>>> listEventsForAccount(@PathVariable(value = "accountId") String accountId) {
        final List<Object> response = accountQueryService.listEventsForAccount(accountId);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }
}
