package com.interswitch.transfer.validation;

import com.interswitch.transfer.Constants;
import com.interswitch.transfer.TransferRequest;
import com.interswitch.transfer.api.Validator;
import com.interswitch.transfer.exceptions.MissingFieldException;

public class FundTransferValidator implements Validator {

    @Override
    public boolean validate(Object obj) throws MissingFieldException {
        
        if(!(obj instanceof TransferRequest)) return false;
        
        TransferRequest transfer = (TransferRequest)obj;
        
        String tempStorage = transfer.getTransferCode();
        
        if(tempStorage instanceof String && tempStorage.length() <= Constants.MAX_TRANSFER_LENGTH) {
            throw new MissingFieldException("requestRef must be set and have a length of atmost 12");
        }
        
        
        return false;
    }

}
