package com.interswitch.transfer.driver;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundsTransfer;
import com.interswitch.transfer.TransferRequest;
import com.interswitch.transfer.codec.AccountValidation;
import com.interswitch.transfer.codec.Bank;
import com.interswitch.transfer.codec.BankResponse;
import com.interswitch.transfer.codec.ErrorResponse;
import com.interswitch.transfer.codec.TransferResponse;

/**
 * 
 * sample code to showcase all the request in transfer service.
 * 
 * For any difficulty, contact any of the contributors for help.
 *
 */
public class AppDriver {

    /**
     * Initiating entity code:
     * This is unique to a each merchant.
     * When you are ready to move to production,
     * you will be provided with your initiatingEntityCode
     */

    private static final String initiatingEntityCode = "XXT";

    /**
     * @clientId:
     * @clientSecret:
     * These are for test environment.
     * private final static String clientId = "IKIA2EFBE1EF63D1BBE2AF6E59100B98E1D3043F477A";
     * private final static String clientSecret = "uAk0Amg6NQwQPcnb9BTJzxvMS6Vz22octQglQ1rfrMA=";
     * FundTransfer transfer = new FundTransfer(clientId, clientSecret, Interswitch.ENV_DEV)
     */

    /**
     * @clientId:
     * @clientSecret:
     * These are for the sandbox environment.
     */
    private final static String clientId = "IKIA6570778A3484D6F33BC7E4165ADCA6CF06B2860A";
    private final static String clientSecret = "DXfUwpuIvMAKN84kv38uspqGOsStgFS0oZMjU7bPwpU=";

    public static void main(String[] args) {

        /**
         * Create a funds transfer object.
         * e.g FundsTransfer transfer = new FundsTransfer(clientId, clientSecret, Interswitch.ENV_SANDBOX);
         * 
         * With this object you can
         * 
         * 1. Get all supported banks on our platform.
         * 
         * e.g BankResponse bankResponse = transfer.fetchBanks();
         * 
         * If successful, it returns a list of all banks. Otherwise it
         * throws returns an error object or throws an exception.
         * 
         * 2. Account Validation
         * 
         * e.g AccountValidation validationResponse = transfer.validateAccount(request);// validate account
         * 
         * This is used to validate an account number against a source bank.
         * If successful, you know for sure the bank account number is valid.
         * Otherwise, it is probably okay to still go on with the transaction.
         * 
         * 3. Funds Transfer.
         * 
         * e.g TransferResponse response = transfer.send(request); // send transfer request
         * 
         * This api, is used to initiate a funds transfer from a sender to a receiver.
         * The sample code is clear and concise and states the mandatory and optional fields.
         * 
         * 
         * 
         */

        FundsTransfer transfer = new FundsTransfer(clientId, clientSecret, Interswitch.ENV_SANDBOX);
        // FundTransfer transfer = new FundTransfer(clientId, clientSecret, Interswitch.ENV_PRODUCTION); // Production
        // FundTransfer transfer = new FundTransfer(clientId, clientSecret); // Defaults to Sandbox

        try {

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
                    .requestRef("14560575603527")// mandatory
                    .senderPhoneNumber("07036913492") // optional
                    .senderEmail("grandeur_man@yahoo.com") // optional
                    .senderLastName("Desmond") // optional
                    .senderOtherNames("Samuel") // optional
                    .receiverPhoneNumber("07036913492") // optional
                    .receiverEmail("grandeur_man@yahoo.com") // optional
                    .receiverLastName("Desmond") // optional
                    .receiverOtherNames("Samuel") // optional
                    .fee("10000")// optional (minor denomination)
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
