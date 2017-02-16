package com.interswitch.transfer.driver;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundTransfer;
import com.interswitch.transfer.TransferRequest;

/**
 * 
 * @author ugochukwu.okeke
 * App to test the different aspects of the package
 *
 */
public class AppDriver {

    public static Interswitch interswitch = new Interswitch("IKIA2EFBE1EF63D1BBE2AF6E59100B98E1D3043F477A", "uAk0Amg6NQwQPcnb9BTJzxvMS6Vz22octQglQ1rfrMA=",Interswitch.ENV_DEV);
    
    public static void main(String[] args){
        
        FundTransfer transfer = FundTransfer.getInstance("AC");
        
        String initCode = transfer.getInitiatingEntityCode();
        
        System.out.println("initiating code is "+initCode);
        
        TransferRequest transferer = new TransferRequest.Builder("PBL")
            .sender("07036913492","grandeur_man@yahoo.com","Desmond","Samuel")
            .beneficiary("07036913492", "grandeur_man@yahoo.com", "Desmond", "Samuel")
            .initiator("15000", "566","CA","7")
            .terminator("15000", "058","566","AC","NG","0114951936","20")
            .mac("F5C858C1DDA905F620BBD59E2559C7809AAC11DA54507BE12EC1673AA9D5F82F0B5EEA7E57FDA3E48CFA1D2E93E4306BF1C5BE917FD42DD858D025D799E86A58")
            .surcharge("10000")
            .transferCode("10360575603527")
            .build();
        
        try {
            transfer.send(transferer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
