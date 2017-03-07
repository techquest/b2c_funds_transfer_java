package com.interswitch.transfer.codec;

public class Termination {
    
    private AccountReceivable accountReceivable;
    
    private String amount;
    private String entityCode;
    private String currencyCode;
    private String paymentMethodCode;
    private String countryCode;
    public Termination(String amount, String entityCode, String currencyCode, String paymentMethodCode, String countryCode){
        this.amount = amount;
        this.entityCode = entityCode;
        this.currencyCode = currencyCode;
        this.paymentMethodCode = paymentMethodCode;
        this.countryCode = countryCode;
    }
    public AccountReceivable getAccountReceivable() {
        return accountReceivable;
    }
    public void setAccountReceivable(AccountReceivable accountReceivable) {
        this.accountReceivable = accountReceivable;
    }
    public String getTerminationAmount() {
        return amount;
    }
    public void setTerminationAmount(String terminationAmount) {
        this.amount = terminationAmount;
    }
    public String getTerminationEntityCode() {
        return entityCode;
    }
    public void setTerminationEntityCode(String terminationEntityCode) {
        this.entityCode = terminationEntityCode;
    }
    public String getTerminationCurrencyCode() {
        return currencyCode;
    }
    public void setTerminationCurrencyCode(String terminationCurrencyCode) {
        this.currencyCode = terminationCurrencyCode;
    }
    public String getTerminationPaymentMethodCode() {
        return paymentMethodCode;
    }
    public void setTerminationPaymentMethodCode(String terminationPaymentMethodCode) {
        this.paymentMethodCode = terminationPaymentMethodCode;
    }
    public String getTerminationCountryCode() {
        return countryCode;
    }
    public void setTerminationCountryCode(String terminationCountryCode) {
        this.countryCode = terminationCountryCode;
    }
}
