package com.interswitch.transfer;

import java.util.HashMap;

import com.google.gson.Gson;
import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.api.Validator;
import com.interswitch.transfer.api.impl.GsonSerializer;
import com.interswitch.transfer.codec.TransferResponse;
import com.interswitch.transfer.utility.Utility;
import com.interswitch.transfer.validation.FundTransferValidator;

public class FundTransfer implements Transfer {

    private Interswitch interswitch;

    private GsonSerializer codec;
    
    private Validator validator;

    public static final String ATM = "1";
    public static final String WEB = "3";
    public static final String MOBILE = "4";
    public static final String KIOSK = "5";
    public static final String PCPOS = "6";
    public static final String POS = "2";
    public static final String LOCATION = "7";
    public static final String DIRECT_DEBIT = "8";
    

    public FundTransfer(String clientId, String clientSecret, String env) {
        this.interswitch = new Interswitch(clientId, clientSecret, env);
        this.codec = new GsonSerializer();
        this.validator = new FundTransferValidator();
        
    }

    public TransferResponse send(TransferRequest tr) throws Exception {
        
        validator.validate(tr);
        
        String mac = Utility.generateMAC(tr);
        
        tr.setMac(mac);
        
        String json = codec.marshall(tr);
        
        System.out.println(json);
        
        HashMap<String, String> extraHeaders = new HashMap<>();
        HashMap<String, String> response=null;
        try {
            response = interswitch.send(Constants.TRANSFER_RESOURCE_URL, Constants.POST, json, extraHeaders);
        } catch (Exception ex) {
            //connection error,
            throw ex;
        }
        
        TransferResponse resp = new TransferResponse();
        
        if(response instanceof HashMap) {
            
            String responseCode = response.get(Interswitch.RESPONSE_CODE);
            String msg = response.get(Interswitch.RESPONSE_MESSAGE);
            Gson g = new Gson();
            resp = g.fromJson(msg, TransferResponse.class);
            
        }
        return resp;
    }

    @Override
    public Object send(Object obj) {
        // TODO Auto-generated method stub
        return null;
    }

    


}
