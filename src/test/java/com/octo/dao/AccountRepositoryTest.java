package com.octo.dao;

import com.octo.entities.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void getAccountByBalanceGreaterThanEqual() {
        accountRepository.save(new Account("c1", new BigDecimal(100)));
        accountRepository.save(new Account("c2", new BigDecimal(200)));
        accountRepository.save(new Account("c3", new BigDecimal(300)));

        List<Account> accounts = accountRepository.getAccountsWhereAmountBetween(new BigDecimal(150), new BigDecimal(250));
        Assertions.assertThat(accounts.size()).isEqualTo(1);
        Assertions.assertThat(accounts.iterator().next().getAccountID()).isEqualTo("c2");
        Assertions.assertThat(accounts.iterator().next().getBalance()).isEqualTo(new BigDecimal(200));

    }

}