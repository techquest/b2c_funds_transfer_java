package example;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundsTransfer;

/**
 * 
 * Account Validation
 * 
 * For any difficulty, contact any of the contributors for help.
 *
 */
public class AccountValidation {
    
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
        
        FundsTransfer transfer = new FundsTransfer(clientId, clientSecret, Interswitch.ENV_SANDBOX);
    }

}
