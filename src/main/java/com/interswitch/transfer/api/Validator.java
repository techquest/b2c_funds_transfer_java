package com.interswitch.transfer.api;

import com.interswitch.transfer.exceptions.InvalidRequestException;
import com.interswitch.transfer.exceptions.MissingFieldException;

public interface Validator {
    
    void validate(Object obj) throws MissingFieldException, InvalidRequestException;
}
