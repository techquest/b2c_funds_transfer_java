package com.interswitch.transfer.driver;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundTransfer;
import com.interswitch.transfer.TransferRequest;
import com.interswitch.transfer.codec.ErrorResponse;
import com.interswitch.transfer.codec.TransferResponse;

public class AppDriver {

    private static final String initiatingEntityCode = "PBL";
    private final static String clientId = "IKIA2EFBE1EF63D1BBE2AF6E59100B98E1D3043F477A";
    private final static String clientSecret = "uAk0Amg6NQwQPcnb9BTJzxvMS6Vz22octQglQ1rfrMA=";

    public static void main(String[] args) {

        /***- START- ***/

        /**
         * Interswitch.ENV_SANDBOX, is for sandbox environment
         * 
         * Interswitch.ENV_PROD, is for production environment
         */
        FundTransfer transfer = new FundTransfer(clientId, clientSecret, Interswitch.ENV_DEV);

        TransferRequest transferer = new TransferRequest.Builder(initiatingEntityCode) // Get your Business Entity Code from Interswitch
            .senderPhoneNumber("07036913492") // optional
            .senderEmail("grandeur_man@yahoo.com") // optional
            .senderLastName("Desmond") // optional
            .senderOtherNames("Samuel") // optional
            .receiverPhoneNumber("07036913492") // optional
            .receiverEmail("grandeur_man@yahoo.com") // optional
            .receiverLastName("Desmond") // optional
            .receiverOtherNames("Samuel") // optional
            .amount("100000") // mandatory, minor denomination
            .channel(FundTransfer.LOCATION) //mandatory: ATM-1, POS-2, WEB-3, MOBILE-4, KIOSK-5, PCPOS-6, LOCATION-7, DIRECT DEBIT-8
            .destinationBankCode("044")/* mandatory:  To be gotten from the get all banks code*/
            .toAccountNumber("0114951936") // mandatory
            .fee("10000")// optional
            .requestRef("80360575603527")// mandatory
            .build();

        try {
            TransferResponse response = transfer.send(transferer);
            
            if(response.getError() instanceof ErrorResponse) {
                /**
                 * NOT SUCCESSFUL
                 */
                String code = response.getError().getCode();
                String message = response.getError().getMessage();
                
            }
            else if(response.getResponseCode().equalsIgnoreCase("90000")) {
                //SUCCESS
            }
            else {
                //NOT SUCCESSFUL
            }
        } catch (Exception e) {
            // contact support at interswitch
            e.printStackTrace();
        }
        /***- FINISH - ***/

    }
}
