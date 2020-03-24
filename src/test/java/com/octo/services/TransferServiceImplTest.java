package com.octo.services;

import com.octo.dao.AccountRepository;
import com.octo.entities.Account;
import com.octo.models.TransferRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TransferServiceImpl.class)
class TransferServiceImplTest {

    @MockBean
    private AccountService accountService;
    @Autowired
    private TransferService transferService;

    @BeforeEach
    public void setUp() {

        Account account1 = new Account("c1", new BigDecimal(100));
        Account account2 = new Account("c2", new BigDecimal(200));

        Mockito.when(accountService.getAccount("c1")).thenReturn(account1);
        Mockito.when(accountService.getAccount("c2")).thenReturn(account2);

        Mockito.when(accountService.setBalance("c1", new BigDecimal(50)))
                .thenReturn(new Account("c1", new BigDecimal(50)));
        Mockito.when(accountService.setBalance("c2", new BigDecimal(250)))
                .thenReturn(new Account("c1", new BigDecimal(250)));
    }
    @Test
    void performTransfer() {

        TransferRequestDTO transferRequestDTO = new TransferRequestDTO(new BigDecimal(50), "c1", "c2");
        transferService.performTransfer(transferRequestDTO);


    }
}