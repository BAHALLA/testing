package com.octo.exceptions;

public class AmountLessThanZeroException extends RuntimeException {
    public AmountLessThanZeroException() {
        super("amount should be greater than 0");
    }
}
