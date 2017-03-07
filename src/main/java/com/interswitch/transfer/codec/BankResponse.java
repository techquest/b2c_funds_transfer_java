package com.interswitch.transfer.codec;

public class BankResponse {

    private Bank[] banks;
    private ErrorResponse error;
    private ErrorResponse[] errors;

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

    public Bank[] getBanks() {
        return banks;
    }

    public void setBanks(Bank[] banks) {
        this.banks = banks;
    }
}
