package com.interswitch.transfer.driver;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundTransfer;
import com.interswitch.transfer.codec.AccountValidation;

public class AccountValidationSample {

    private final static String clientId = "IKIA2EFBE1EF63D1BBE2AF6E59100B98E1D3043F477A";
    private final static String clientSecret = "uAk0Amg6NQwQPcnb9BTJzxvMS6Vz22octQglQ1rfrMA=";

    public static void main(String[] args) {

        FundTransfer transfer = new FundTransfer(clientId, clientSecret, Interswitch.ENV_DEV);
        
        /**
         * Given an account number and bankCode,
         * To determine if the account is valid.
         */
        
        try{
            AccountValidation response = transfer.validateAccount("076", "3011747903");
            
            if(response.getError() == null) { //not successful
                
            }
            else {
                
                //successful
                String accountNumber = response.getAccountNumber();
                String accountName = response.getAccountName();
                String lastName = response.getFirstName();
                String otherNames = response.getOtherNames();
                String accountType = response.getAccountType();
                String accountCurrency  = response.getAccountCurrency();
                String firstAddress = response.getAddrLine1();
                String secondAddress = response.getAddrLine2();
                String city = response.getCity();
                String phone = response.getPhone();
                String postalCode = response.getPostalCode();
                String stateCode = response.getStateCode();
                
                
                
            }
        }
        catch(Exception ex) {
            
        }
    }
}
