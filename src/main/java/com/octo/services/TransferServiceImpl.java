package com.octo.services;

import com.octo.entities.Account;
import com.octo.models.TransferRequestDTO;
import com.octo.validators.TransferValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountService accountService;
    private final TransferValidator transferValidator;

    @Override
    public void performTransfer(TransferRequestDTO transferRequestDTO) {

        transferValidator.validateTransferRequest(transferRequestDTO);

        Account senderAccount =  accountService.getAccount(transferRequestDTO.getSenderAccountID());
        Account receiverAccount =  accountService.getAccount(transferRequestDTO.getReceiverAccountID());
        BigDecimal amount = transferRequestDTO.getAmount();

        BigDecimal senderAccountNewBalance = senderAccount.getBalance().subtract(amount);
        accountService.setBalance(senderAccount.getAccountID() ,  senderAccountNewBalance);

        BigDecimal receiverAccountNewBalance = receiverAccount.getBalance().add(amount);
        accountService.setBalance(receiverAccount.getAccountID(), receiverAccountNewBalance);

    }
}
