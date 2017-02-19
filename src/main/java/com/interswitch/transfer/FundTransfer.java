package com.interswitch.transfer;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.techquest.auth.utils.ConstantUtils;
import com.interswitch.transfer.api.impl.GsonSerializer;
import com.interswitch.transfer.driver.AppDriver;

public class FundTransfer implements Transfer {
    
    private String initiatingEntityCode;
    private String clientId;
    private String clientSecret;
    
    private Interswitch interswitch;
    
    private GsonSerializer codec;
    
    public FundTransfer(String clientId, String clientSecret, String initiatingEntityCode) {
        this.initiatingEntityCode=initiatingEntityCode;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.interswitch = new Interswitch(clientId, clientSecret,Interswitch.ENV_DEV);
        codec = new GsonSerializer();
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getInitiatingEntityCode() {
        return initiatingEntityCode;
    }

    public Object send(TransferRequest tr, Map<String, String> extraHeaders) throws Exception {
        
        String json = codec.marshall(tr);
        System.out.println(json);
        Map<String, String> interswitchResponse = interswitch.send(Constants.TRANSFER_RESOURCE_URL, Constants.POST, json,extraHeaders);
        return interswitchResponse;
        //return null;
    }

    public Object send() {
        // TODO Auto-generated method stub
        return null;
    }

}
