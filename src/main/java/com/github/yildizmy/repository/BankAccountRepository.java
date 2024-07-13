package com.github.yildizmy.repository;

import com.github.yildizmy.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

    boolean existsBankAccountById(UUID accountId);
}
