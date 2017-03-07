# B2C-Funds-Transfer
```
package com.interswitch.transfer.driver;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundsTransfer;
import com.interswitch.transfer.TransferRequest;
import com.interswitch.transfer.codec.AccountValidation;
import com.interswitch.transfer.codec.Bank;
import com.interswitch.transfer.codec.BankResponse;
import com.interswitch.transfer.codec.ErrorResponse;
import com.interswitch.transfer.codec.TransferResponse;

public class AppDriver {

    private static final String initiatingEntityCode = "PBL";
    
    //test clientId and clientSecret
    //private final static String clientId = "IKIA2EFBE1EF63D1BBE2AF6E59100B98E1D3043F477A";
    //private final static String clientSecret = "uAk0Amg6NQwQPcnb9BTJzxvMS6Vz22octQglQ1rfrMA=";
    
    // sandbox clienId client
    private final static String clientId = "IKIA6570778A3484D6F33BC7E4165ADCA6CF06B2860A";
    private final static String clientSecret = "DXfUwpuIvMAKN84kv38uspqGOsStgFS0oZMjU7bPwpU=";

    public static void main(String[] args) {

        /***- START- ***/

        FundsTransfer transfer = new FundsTransfer(clientId, clientSecret, Interswitch.ENV_SANDBOX);
        //FundTransfer transfer = new FundTransfer(clientId, clientSecret, Interswitch.ENV_PRODUCTION); // Production
        //FundTransfer transfer = new FundTransfer(clientId, clientSecret); // Defaults to Sandbox

        try {
            
            // get all banks request
            BankResponse bankResponse = transfer.fetchBanks();

            Bank[] bank = bankResponse.getBanks(); // a bank array of all banks

            if (bank instanceof Object) {

                // successful
                Bank testBank = bank[0]; // bank at index 0

                String cbnCode = testBank.getCbnCode(); // Central bank code
                String bankName = testBank.getBankName(); // bank name:
                String bankCode = testBank.getBankCode(); // bankcode in alphabetical form: UBA, GTB, FBN

                // build transfer request
                TransferRequest request = new TransferRequest.Builder(initiatingEntityCode) // Get your Business Entity Code from Interswitch
                    .amount("100000") // mandatory, minor denomination
                    .channel(FundsTransfer.LOCATION) // mandatory: ATM-1, POS-2, WEB-3, MOBILE-4, KIOSK-5, PCPOS-6, LOCATION-7, DIRECT DEBIT-8
                    .destinationBankCode(cbnCode)/* mandatory:  To be gotten from the get all banks code (transfer.fetchBanks())*/
                    .toAccountNumber("0114951936") // mandatory
                    .requestRef("60360575603527")// mandatory
                    .senderPhoneNumber("07036913492") // optional
                    .senderEmail("grandeur_man@yahoo.com") // optional
                    .senderLastName("Desmond") // optional
                    .senderOtherNames("Samuel") // optional
                    .receiverPhoneNumber("07036913492") // optional
                    .receiverEmail("grandeur_man@yahoo.com") // optional
                    .receiverLastName("Desmond") // optional
                    .receiverOtherNames("Samuel") // optional
                    .fee("10000")// optional
                    .build();

                AccountValidation validationResponse = transfer.validateAccount(request);// validate account

                if (validationResponse instanceof AccountValidation) {
                    String accountName = validationResponse.getAccountName();
                }

                TransferResponse response = transfer.send(request); // send transfer request

                if (response.getError() instanceof ErrorResponse) {
                    // NOT SUCCESSFUL
                    String code = response.getError()
                        .getCode();
                    String message = response.getError()
                        .getMessage();

                } else if (response.getResponseCode()
                    .equalsIgnoreCase("90000")) {

                    // SUCCESS
                    String mac = response.getMac();
                    String transactionDate = response.getTransactionDate();
                    String responseCode = response.getResponseCode();

                } else {
                    // transfer was not successful
                }

            } else {

            }
        } catch (Exception ex) {

        }

    }
}
```