package com.octo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class TransferRequestDTO {
    private BigDecimal amount;
    private String senderAccountID;
    private String receiverAccountID;
}
