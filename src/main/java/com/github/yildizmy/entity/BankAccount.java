package com.github.yildizmy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"owner", "balance"})
public class BankAccount {

    @Id
    private UUID id;
    private String owner;
    private BigDecimal balance;
}
