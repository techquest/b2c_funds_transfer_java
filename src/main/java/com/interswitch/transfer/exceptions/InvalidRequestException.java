package com.interswitch.transfer.exceptions;

@SuppressWarnings("serial")
public class InvalidRequestException extends Exception {

    public InvalidRequestException(String msg) {
        super(msg);
    }
}
