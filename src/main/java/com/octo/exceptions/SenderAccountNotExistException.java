package com.octo.exceptions;

public class SenderAccountNotExistException extends RuntimeException {

    public SenderAccountNotExistException() {

        super("Sender account doesn't exist !!");
    }
}
