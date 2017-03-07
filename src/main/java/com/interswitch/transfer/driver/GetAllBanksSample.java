package com.interswitch.transfer.driver;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundsTransfer;
import com.interswitch.transfer.codec.Bank;
import com.interswitch.transfer.codec.BankResponse;

public class GetAllBanksSample {

    private final static String clientId = "IKIA2EFBE1EF63D1BBE2AF6E59100B98E1D3043F477A";
    private final static String clientSecret = "uAk0Amg6NQwQPcnb9BTJzxvMS6Vz22octQglQ1rfrMA=";

    public static void main(String[] args) {

        /**
         * Interswitch.ENV_SANDBOX, is for sandbox environment
         * 
         * Interswitch.ENV_PROD, is for production environment
         */
        FundsTransfer transfer = new FundsTransfer(clientId, clientSecret, Interswitch.ENV_DEV);

        try {

            BankResponse response = transfer.fetchBanks(); // makes network call

            Bank[] bank = response.getBanks(); // a bank array of all banks

            if (bank instanceof Object) {

                // successful
                Bank testBank = bank[0]; // bank at index 0

                String cbnCode = testBank.getCbnCode(); // Central bank code
                String bankName = testBank.getBankName(); // bank name: 
                String bankCode = testBank.getBankCode(); // bankcode in alphabetical form: UBA, GTB, FBN

            } else {

                // error occured
            }

        } catch (

        Exception ex) {
            // request was not completed
        }
    }
}
