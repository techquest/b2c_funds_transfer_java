package com.interswitch.transfer;

import java.util.HashMap;

import com.google.gson.Gson;
import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.codec.AccountReceivable;
import com.interswitch.transfer.codec.AccountValidation;
import com.interswitch.transfer.codec.Beneficiary;
import com.interswitch.transfer.codec.Initiation;
import com.interswitch.transfer.codec.Sender;
import com.interswitch.transfer.codec.Termination;

public class TransferRequest {

    private Sender sender;
    private Beneficiary beneficiary;

    private Initiation initiation;
    private Termination termination;

    private String mac;
    private String surcharge;

    private String initiatingEntityCode;

    private String transferCode;
    
    private String accountNumber;
    private String bankCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public static class Builder {

        private Sender sender;

        private String senderPhoneNumber = "-";
        private String senderEmail = "-";
        private String senderLastName = "-";
        private String senderOtherNames = "-";

        private Beneficiary beneficiary;
        private String beneficiaryPhoneNumber;
        private String beneficiaryEmail;
        private String beneficiaryLastName;
        private String beneficiaryOtherNames;

        private String amount;

        private String initiatorAmount;
        private String initiatorCurrencyCode = Constants.CURRENCY_CODE;
        private String initiatorPaymentMethodCode = Constants.INITATION_PAYMENT_METHOD_CODE;
        private String initiatorChannel;

        private String terminationAmount;
        private String terminationEntityCode;
        private String terminationCurrencyCode = Constants.CURRENCY_CODE;
        private String terminationPaymentMethodCode = Constants.TERMINATION_PAYMENT_METHOD_CODE;
        private String terminationCountryCode = Constants.COUNTRY_CODE;
        private String terminationAccountNumber;
        private String terminationAccountType = Constants.ACCOUNT_TYPE;

        private Initiation initiation;
        private Termination termination;

        private String mac;
        private String surcharge;

        private String initiatingEntityCode;

        private String transferCode;

        public Builder(String initiatingEntityCode) {

            this.initiatingEntityCode = initiatingEntityCode;

        }

        public Builder receiverPhoneNumber(String phone) {

            this.beneficiaryPhoneNumber = phone;
            return this;
        }

        public Builder receiverEmail(String email) {

            this.beneficiaryEmail = email;
            return this;
        }

        public Builder receiverLastName(String lastName) {

            this.beneficiaryLastName = lastName;
            return this;
        }

        public Builder receiverOtherNames(String otherName) {

            this.beneficiaryOtherNames = otherName;
            return this;
        }

        public Builder senderPhoneNumber(String phone) {
            this.senderPhoneNumber = phone;
            return this;
        }

        public Builder senderEmail(String email) {

            this.senderEmail = email;
            return this;
        }

        public Builder senderLastName(String lastName) {

            this.senderLastName = lastName;
            return this;
        }

        public Builder senderOtherNames(String otherName) {

            this.senderOtherNames = otherName;
            return this;
        }

        public Builder setAmount(String amount) {

            this.amount = amount;
            return this;
        }

        public Builder amount(String amount) {
            this.initiatorAmount = amount;
            this.amount = amount;
            return this;
        }

        public Builder currencyCode(String currencyCode) {
            this.initiatorCurrencyCode = currencyCode;
            return this;
        }

        public Builder paymentMethodCode(String paymentMethodCode) {
            this.initiatorPaymentMethodCode = paymentMethodCode;
            return this;
        }

        public Builder channel(String channel) {
            this.initiatorChannel = channel;
            return this;
        }

        public Builder terminationCurrencyCode(String currencyCode) {
            this.terminationCurrencyCode = currencyCode;
            return this;
        }

        public Builder terminationPaymentMethodCode(String paymentMethodCode) {
            this.terminationPaymentMethodCode = paymentMethodCode;
            return this;
        }

        public Builder terminationCountryCode(String countryCode) {
            this.terminationCountryCode = countryCode;
            return this;
        }

        public Builder toAccountNumber(String accountNumber) {
            this.terminationAccountNumber = accountNumber;
            return this;
        }

        public Builder destinationBankCode(String entityCode) {
            this.terminationEntityCode = entityCode;
            
            return this;
        }
        public Builder terminationAccountType(String accountType) {
            this.terminationAccountType = accountType;
            return this;
        }

        public Builder requestRef(String ref) {

            this.transferCode = ref;

            return this;
        }

        public Builder fee(String surcharge) {

            this.surcharge = surcharge;

            return this;
        }

        public TransferRequest build() {

            return new TransferRequest(this);
        }
    }

    private TransferRequest(Builder builder) {
        // set data to the Transfer Request
        sender = new Sender(builder.senderPhoneNumber, builder.senderEmail, builder.senderLastName, builder.senderOtherNames);
        beneficiary = new Beneficiary(builder.beneficiaryPhoneNumber, builder.beneficiaryEmail, builder.beneficiaryLastName, builder.beneficiaryOtherNames);
        initiation = new Initiation(builder.initiatorAmount, builder.initiatorCurrencyCode, builder.initiatorPaymentMethodCode, builder.initiatorChannel);
        AccountReceivable tmpAccount = new AccountReceivable(builder.terminationAccountNumber, builder.terminationAccountType);
        termination = new Termination(builder.amount, builder.terminationEntityCode, builder.terminationCurrencyCode,builder.terminationPaymentMethodCode, builder.terminationCountryCode);
        termination.setAccountReceivable(tmpAccount);
        surcharge = builder.surcharge;
        transferCode = builder.transferCode;
        initiatingEntityCode = builder.initiatingEntityCode;
        this.setAccountNumber(builder.terminationAccountNumber);
        this.setBankCode(builder.terminationEntityCode);

    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Sender getSender() {
        return sender;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public Initiation getInitiation() {
        return initiation;
    }

    public Termination getTermination() {
        return termination;
    }

    public String getMac() {
        return mac;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public String getInitiatingEntityCode() {
        return initiatingEntityCode;
    }

    public String getTransferCode() {
        return transferCode;
    }

    public AccountValidation validateAccount() throws Exception {
        // TODO Auto-generated method stub
            String bankCode = this.getBankCode();
            String accountNumber = this.getTransferCode();
            HashMap<String, String> extraHeaders = new HashMap<String, String>();
            String url = Constants.ACCOUNT_VALIDATION_URL_PREFIX + bankCode + "/"+ Constants.ACCOUNT_VALIDATION_URL_SUFFIX + accountNumber+"/names";
            HashMap<String, String> response = FundsTransfer.interswitch.send(url, Constants.GET, "", extraHeaders);

            String responseCode = response.get(Interswitch.RESPONSE_CODE);
            String msg = response.get(Interswitch.RESPONSE_MESSAGE);
            Gson g = new Gson();
            AccountValidation resp = g.fromJson(msg, AccountValidation.class);

            return resp;
    }
}
