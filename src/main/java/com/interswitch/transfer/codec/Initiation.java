package com.interswitch.transfer.codec;

public class Initiation {
    
    private String amount;
    private String currencyCode;
    private String paymentMethodCode;
    private String channel;
    
    public Initiation(String amount, String currencyCode, String paymentMethodCode, String channel){
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.paymentMethodCode = paymentMethodCode;
        this.channel = channel;
    }
    
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }
    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
}
    