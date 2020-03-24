package com.octo.exceptions;

public class BalanceLessThanTransferAmountException extends RuntimeException{

    public BalanceLessThanTransferAmountException() {
        super("Amount is greater than account balance !! ");
    }
}
