package com.octo.validators;

import com.octo.entities.Account;
import com.octo.exceptions.AmountLessThanZeroException;
import com.octo.exceptions.BalanceLessThanTransferAmountException;
import com.octo.exceptions.ReceiverAccountNotExistException;
import com.octo.exceptions.SenderAccountNotExistException;
import com.octo.models.TransferRequestDTO;
import com.octo.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = TransferValidatorImpl.class)
@ExtendWith(SpringExtension.class)
class TransferValidatorImplTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private TransferValidatorImpl transferValidator;

    @BeforeEach
    public void setup() {
        Account account1 = new Account("c1", new BigDecimal(100));
        Account account2 = new Account("c2", new BigDecimal(200));
        Mockito.when(accountService.getAccount("c1")).thenReturn(account1);
        Mockito.when(accountService.getAccount("c2")).thenReturn(account2);

    }

    @Test
    public void whenAmountLessThanZero_thanAmountLessThanZeroException() {
        TransferRequestDTO transferRequestDTO =
                new TransferRequestDTO(new BigDecimal( -100), "c1", "c2");

        assertThatExceptionOfType(AmountLessThanZeroException.class).isThrownBy(()-> {
            transferValidator.validateTransferRequest(transferRequestDTO);
        });
    }

    @Test
    public void whenSenderAccountNotExist_thanSenderAccountNotExistException() {
        TransferRequestDTO transferRequestDTO =
                new TransferRequestDTO(new BigDecimal( 100), "c0", "c2");
        assertThatExceptionOfType(SenderAccountNotExistException.class).isThrownBy(()-> {
            transferValidator.validateTransferRequest(transferRequestDTO);
        });
    }

    @Test
    public void whenReceiverAccountNotExist_thanReceiverAccountNotExistException() {
        TransferRequestDTO transferRequestDTO =
                new TransferRequestDTO(new BigDecimal( 100), "c1", "c3");
        assertThatExceptionOfType(ReceiverAccountNotExistException.class).isThrownBy(()-> {
            transferValidator.validateTransferRequest(transferRequestDTO);
        });
    }

    @Test
    public void whenBalanceLessThanTransferAmount_thanBalanceLessThanTransferAmountException() {
        TransferRequestDTO transferRequestDTO =
                new TransferRequestDTO(new BigDecimal( 200), "c1", "c3");
        assertThatExceptionOfType(BalanceLessThanTransferAmountException.class).isThrownBy(()-> {
            transferValidator.validateTransferRequest(transferRequestDTO);
        });
    }
}