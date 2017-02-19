package com.interswitch.transfer.api;

import com.interswitch.transfer.exceptions.MissingFieldException;

public interface Validator {
    
    boolean validate(Object obj) throws MissingFieldException;
}
