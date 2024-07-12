package com.github.yildizmy.dto.mapper;

import com.github.yildizmy.dto.response.BankAccountResponse;
import com.github.yildizmy.entity.BankAccount;

public class BankAccountMapper {

    public static BankAccountResponse mapToResponse(BankAccount bankAccount) {
        return new BankAccountResponse(
                bankAccount.getId(),
                bankAccount.getOwner(),
                bankAccount.getBalance()
        );
    }
}
