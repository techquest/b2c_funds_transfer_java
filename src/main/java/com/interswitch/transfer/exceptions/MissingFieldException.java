package com.interswitch.transfer.exceptions;

@SuppressWarnings("serial")
public class MissingFieldException extends Exception {
    
    public MissingFieldException(String msg) {
        super(msg);
    }
}
