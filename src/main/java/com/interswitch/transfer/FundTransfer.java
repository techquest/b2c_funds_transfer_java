package com.interswitch.transfer;

import java.util.HashMap;

import com.google.gson.Gson;
import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.techquest.auth.utils.ConstantUtils;
import com.interswitch.transfer.driver.AppDriver;

public class FundTransfer implements Transfer {
    
    private String initiatingEntityCode;
    
    private static FundTransfer instance;
    
    private FundTransfer(){
        
    }
    
    public static FundTransfer getInstance(String initiatingEntityCode){
        if(!(instance instanceof FundTransfer)) {
            instance = new FundTransfer();
            instance.initiatingEntityCode = initiatingEntityCode;
        }
        return instance;
    }

    public String getInitiatingEntityCode() {
        return initiatingEntityCode;
    }

    public Object send(TransferRequest tr) throws Exception {
        
        // create an instance of TransferRequest
//        /TransferRequest transfer
        Gson gson = new Gson();
        String json = gson.toJson(tr);
        System.out.println(json);
        HashMap<String, String> interswitchResponse;
        HashMap<String, String> extraHeaders = new HashMap<String, String>();
        extraHeaders.put(Interswitch.TERMINAL_ID, "3PBL0001");
        String httpMethod = ConstantUtils.POST;
        String resourceUrl = "api/v1/quickteller/payments/transfers";
        interswitchResponse = AppDriver.interswitch.send(resourceUrl, httpMethod, json,extraHeaders);
        return interswitchResponse;
        //return null;
    }

    public Object send() {
        // TODO Auto-generated method stub
        return null;
    }

}
