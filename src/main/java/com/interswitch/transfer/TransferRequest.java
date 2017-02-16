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
    
    
    
    
    
    public static class Builder{
        
        private Sender sender;
        private Beneficiary beneficiary;
        
        private Initiation initiation;
        private Termination termination;
        
        private String mac;
        private String surcharge;
        
        private String initiatingEntityCode;
        
        private String transferCode;
        
        
        public Builder(String initiatingEntityCode){
            
            this.initiatingEntityCode=initiatingEntityCode;
            
        }
        
        public Builder sender(
            String phone,
            String email, 
            String lastName,
            String otherNames){
            
            Sender sender = new Sender();
            sender.setEmail(email);
            sender.setOtherNames(otherNames);
            sender.setLastName(lastName);
            sender.setPhone(phone);
            
            this.sender = sender;
            
            
            return this;
        }
        public Builder beneficiary(
            String phone,
            String email, 
            String lastName,
            String otherNames){
            
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setEmail(email);
            beneficiary.setPhone(phone);
            beneficiary.setLastName(lastName);
            beneficiary.setOtherNames(otherNames);
            
            this.beneficiary = beneficiary;
            
            return this;
        }
        
        public Builder initiator(
            String initiatingAmount,
            String initiatingCurrencyCode,
            String initiatingPaymentMethodCode,
            String initiatingChannel){
            
            Initiation init = new Initiation();
            init.setAmount(initiatingAmount);
            init.setChannel(initiatingChannel);
            init.setCurrencyCode(initiatingCurrencyCode);
            init.setPaymentMethodCode(initiatingPaymentMethodCode);
            
            this.initiation = init;
            
            
            return this;
        }
        
        public Builder terminator(
            String terminationAmount,
            String terminationEntityCode,
            String terminationCurrencyCode,
            String terminationPaymentMethodCode,
            String terminationCountryCode,
            String accountNumber,
            String accountType){
            
            AccountReceivable tmpAccount = new AccountReceivable();
            tmpAccount.setAccountNumber(accountNumber);
            tmpAccount.setAccountType(accountType);
            
            Termination term = new Termination();
            term.setAccountReceivable(tmpAccount);
            
            term.setTerminationAmount(terminationAmount);
            term.setTerminationEntityCode(terminationEntityCode);
            term.setTerminationCountryCode(terminationCountryCode);
            term.setTerminationCurrencyCode(terminationCurrencyCode);
            term.setTerminationPaymentMethodCode(terminationPaymentMethodCode);
            
            this.termination= term;
            
            
            return this;
        }
        
        public Builder transferCode(String transferCode){
            
            this.transferCode=transferCode;
            
            return this;
        }
        
        public Builder mac(String mac){
            
            this.mac = mac;
            
            return this;
        }
        
        public Builder surcharge(String surcharge){
            
            this.surcharge = surcharge;
            
            return this;
        }
        
        
        
        public TransferRequest build() {
            
            return new TransferRequest(this);
        }
    }
    
    private TransferRequest(Builder builder) {
        //set data to the Transfer Request
        sender = builder.sender;
        beneficiary = builder.beneficiary;
        initiation = builder.initiation;
        termination = builder.termination;
        mac = builder.mac;
        surcharge = builder.surcharge;
        transferCode = builder.transferCode;
        initiatingEntityCode = builder.initiatingEntityCode;
        
    }
}
