package com.octo.services;

import com.octo.entities.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account getAccount(String accountID);
    BigDecimal getBalance(String accountID);
    Account setBalance(String accountID, BigDecimal balance);
}
