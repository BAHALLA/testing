package com.octo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Account {
    @Id
    private String accountID;
    private BigDecimal balance;
}
