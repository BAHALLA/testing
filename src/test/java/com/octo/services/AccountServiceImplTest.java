package com.octo.services;

import com.octo.dao.AccountRepository;
import com.octo.entities.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccountServiceImpl.class)
class AccountServiceImplTest {

    @MockBean
    private AccountRepository accountRepository ;
    @Autowired
    private AccountServiceImpl accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("c1", new BigDecimal(100));
        Mockito.when(accountRepository.findById(account.getAccountID()).get()).thenReturn( account);

    }
    @Test
    void getAccount() {
        Account returnedAccount = accountService.getAccount(account.getAccountID());
        assertThat(returnedAccount).isNotNull();
        assertThat(returnedAccount.getAccountID()).isEqualTo("c1");
        assertThat(returnedAccount.getBalance()).isEqualTo(new BigDecimal(100));

    }

    @Test
    void getBalance() {
        BigDecimal returnedBalance = accountService.getBalance(account.getAccountID());
        assertThat(returnedBalance).isEqualTo(new BigDecimal(100));
    }

    @Test
    void setBalance() {
        Account updatedAccount = new  Account("c1", new BigDecimal(200));
        Mockito.when(accountRepository.save(updatedAccount)).thenReturn(updatedAccount);

        Account account1 = accountService.setBalance("c1", new BigDecimal(200));
        assertThat(account1.getAccountID()).isEqualTo("c1");
        assertThat(account1.getBalance()).isEqualTo(new BigDecimal(200));
    }


}