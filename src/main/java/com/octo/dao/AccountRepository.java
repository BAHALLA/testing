package com.octo.dao;

import com.octo.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("select a from Account a where a.balance >= :val1 and a.balance <= :val2")
    List<Account> getAccountsWhereAmountBetween(@Param("val1") BigDecimal val1, @Param("val2") BigDecimal val2 );
}
