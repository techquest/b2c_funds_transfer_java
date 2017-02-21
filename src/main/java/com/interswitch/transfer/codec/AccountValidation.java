package com.interswitch.transfer.codec;

public class AccountValidation {

    public AccountValidation() {

    }

    

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    private String accountName;
    
    private ErrorResponse error;

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public ErrorResponse[] getErrors() {
        return errors;
    }

    public void setErrors(ErrorResponse[] errors) {
        this.errors = errors;
    }

    private ErrorResponse[] errors;

}
