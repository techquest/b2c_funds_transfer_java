package com.interswitch.transfer.driver;

import java.util.HashMap;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.Constants;
import com.interswitch.transfer.FundTransfer;
import com.interswitch.transfer.TransferRequest;

public class AppDriver {

    private static final String initiatingEntityCode = "PBL";
    private static final String terminalId = "3PBL0001";
    private final static String clientId = "IKIA2EFBE1EF63D1BBE2AF6E59100B98E1D3043F477A";
    private final static String clientSecret = "uAk0Amg6NQwQPcnb9BTJzxvMS6Vz22octQglQ1rfrMA=";

    public static void main(String[] args) {

        /***- START- ***/
        FundTransfer transfer = new FundTransfer(clientId, clientSecret, initiatingEntityCode);

        TransferRequest transferer = new TransferRequest.Builder("PBL").senderPhoneNumber("07036913492")
            .senderEmail("grandeur_man@yahoo.com")
            .senderLastName("Desmond")
            .senderOtherNames("Samuel")
            .beneficiaryPhoneNumber("07036913492")
            .beneficiaryEmail("grandeur_man@yahoo.com")
            .beneficiaryLastName("Desmond")
            .beneficiaryOtherNames("Samuel")
            .setAmount("15000")
            .initiatorChannel("7")
            .terminationEntityCode("044")
            .terminationAccountNumber("0114951936")
            .surcharge("10000")
            .requestRef("10360575603527")
            .build();

        // headers
        HashMap<String, String> H = new HashMap<>();
        H.put(Constants.TERMINAL_ID, terminalId);

        try {
            transfer.send(transferer, H);
        } catch (Exception e) {
            //contact support at interswitch
            e.printStackTrace();
        }
        /***- FINISH - ***/

    }
}
