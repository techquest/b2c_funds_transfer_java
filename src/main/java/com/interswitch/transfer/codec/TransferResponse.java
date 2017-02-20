package com.interswitch.transfer.codec;

public class TransferResponse {

    private String mac;
    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getTransferCode() {
        return transferCode;
    }
    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    
    private String transactionDate;
    private String transferCode;
    private String pin;
    private String responseCode;
    private ErrorResponse[] errors;
    public ErrorResponse[] getErrors() {
        return errors;
    }
    public void setErrors(ErrorResponse[] errors) {
        this.errors = errors;
    }
    public ErrorResponse getError() {
        return error;
    }
    public void setError(ErrorResponse error) {
        this.error = error;
    }

    private ErrorResponse error;
    
    
    
}
