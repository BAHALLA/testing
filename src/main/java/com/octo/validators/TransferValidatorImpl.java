package com.octo.validators;

import com.octo.entities.Account;
import com.octo.exceptions.AmountLessThanZeroException;
import com.octo.exceptions.BalanceLessThanTransferAmountException;
import com.octo.exceptions.ReceiverAccountNotExistException;
import com.octo.exceptions.SenderAccountNotExistException;
import com.octo.models.TransferRequestDTO;
import com.octo.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TransferValidatorImpl implements TransferValidator {


    private final AccountService accountService;

    @Override
    public void validateTransferRequest(TransferRequestDTO transferRequestDTO) {

        String senderAccountID = transferRequestDTO.getSenderAccountID();
        String receiverAccountID = transferRequestDTO.getReceiverAccountID();
        Account senderAccount = accountService.getAccount(senderAccountID);
        Account receiverAccount = accountService.getAccount(receiverAccountID);

        if(transferRequestDTO.getAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new AmountLessThanZeroException();
        }
        if(senderAccount == null) {
            throw new SenderAccountNotExistException();
        }
        if(senderAccount.getBalance().compareTo(transferRequestDTO.getAmount()) < 0) {
            throw  new BalanceLessThanTransferAmountException();
        }
        if(receiverAccount == null) {
            throw new ReceiverAccountNotExistException();
        }

    }
}
