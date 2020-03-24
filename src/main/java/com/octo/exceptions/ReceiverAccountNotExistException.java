package com.octo.exceptions;

public class ReceiverAccountNotExistException extends RuntimeException {

    public ReceiverAccountNotExistException() {
        super("Receiver account does not exist !!");
    }
}
