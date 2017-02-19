package com.interswitch.transfer;

import com.interswitch.transfer.codec.AccountReceivable;
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
        private String terminationCurrencyCode;
        private String terminationPaymentMethodCode;
        private String terminationCountryCode = Constants.CURRENCY_CODE;
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

        public Builder beneficiaryPhoneNumber(String phone) {

            this.beneficiaryPhoneNumber = phone;
            return this;
        }

        public Builder beneficiaryEmail(String email) {

            this.beneficiaryEmail = email;
            return this;
        }

        public Builder beneficiaryLastName(String lastName) {

            this.beneficiaryLastName = lastName;
            return this;
        }

        public Builder beneficiaryOtherNames(String otherName) {

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

        public Builder initiatorAmount(String amount) {
            this.initiatorAmount = amount;
            return this;
        }

        public Builder initiatorCurrencyCode(String currencyCode) {
            this.initiatorCurrencyCode = currencyCode;
            return this;
        }

        public Builder initiatorPaymentMethodCode(String paymentMethodCode) {
            this.initiatorPaymentMethodCode = paymentMethodCode;
            return this;
        }

        public Builder initiatorChannel(String channel) {
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

        public Builder terminationAccountNumber(String accountNumber) {
            this.terminationAccountNumber = accountNumber;
            return this;
        }

        public Builder terminationEntityCode(String entityCode) {
            this.terminationEntityCode = entityCode;
            return this;
        }
        public Builder terminationAccountType(String accountType) {
            this.terminationAccountType = accountType;
            return this;
        }

        public Builder transferCode(String transferCode) {

            this.transferCode = transferCode;

            return this;
        }

        public Builder requestRef(String ref) {

            this.transferCode = ref;

            return this;
        }

        public Builder surcharge(String surcharge) {

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
        initiation = new Initiation(builder.amount, builder.initiatorCurrencyCode, builder.initiatorPaymentMethodCode, builder.initiatorChannel);
        AccountReceivable tmpAccount = new AccountReceivable(builder.terminationAccountNumber, builder.terminationAccountType);
        termination = new Termination(builder.amount, builder.terminationEntityCode, builder.terminationCurrencyCode,builder.terminationPaymentMethodCode, builder.terminationCountryCode);
        termination.setAccountReceivable(tmpAccount);
        surcharge = builder.surcharge;
        transferCode = builder.transferCode;
        initiatingEntityCode = builder.initiatingEntityCode;

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
}
