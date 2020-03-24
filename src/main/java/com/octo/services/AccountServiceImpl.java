package com.octo.services;

import com.octo.dao.AccountRepository;
import com.octo.entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account getAccount(String accountID) {
        return accountRepository.findById(accountID).get();
    }

    @Override
    public BigDecimal getBalance(String accountID) {
        return accountRepository.findById(accountID).get().getBalance();
    }

    @Override
    public Account setBalance(String accountID, BigDecimal balance) {
        Account account = accountRepository.findById(accountID).get();
        account.setBalance(balance);
        return accountRepository.save(account);
    }


}
