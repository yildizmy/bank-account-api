package com.github.yildizmy.service;

import com.github.yildizmy.coreapi.queries.FindBankAccountQuery;
import com.github.yildizmy.dto.mapper.BankAccountMapper;
import com.github.yildizmy.dto.response.BankAccountResponse;
import com.github.yildizmy.entity.BankAccount;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.github.yildizmy.common.Util.formatUuid;

@Service
@RequiredArgsConstructor
public class AccountQueryService {

    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public BankAccountResponse findById(String accountId) throws ExecutionException, InterruptedException {
        return queryGateway.query(
                new FindBankAccountQuery(formatUuid(accountId)),
                ResponseTypes.instanceOf(BankAccount.class)
        ).thenApply(BankAccountMapper::mapToResponse).get();
    }

    public List<Object> listEventsForAccount(String accountId) {
        return eventStore
                .readEvents(formatUuid(accountId).toString())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}
