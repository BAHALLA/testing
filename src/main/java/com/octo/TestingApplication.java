package com.octo;

import com.octo.dao.AccountRepository;
import com.octo.entities.Account;
import com.octo.models.TransferRequestDTO;
import com.octo.services.TransferService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(TransferService transferService, AccountRepository accountRepository) {
        return args -> {

            accountRepository.save(new Account("c1", new BigDecimal(100)));
            accountRepository.save(new Account("c2", new BigDecimal(200)));
            accountRepository.save(new Account("c3", new BigDecimal(300)));

           // System.out.println(accountRepository.getAccountsWhereAmountBetween(new BigDecimal(150), new BigDecimal(250)));

            TransferRequestDTO transferRequestDTO = new TransferRequestDTO();
            transferRequestDTO.setAmount(new BigDecimal(50));
            transferRequestDTO.setSenderAccountID("c1");
            transferRequestDTO.setReceiverAccountID("c2");
            transferService.performTransfer(transferRequestDTO);
            System.out.println(accountRepository.findById("c2").get().getBalance());
        };
    };

}
