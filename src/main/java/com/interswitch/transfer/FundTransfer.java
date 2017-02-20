package com.interswitch.transfer;

import java.util.HashMap;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.api.Validator;
import com.interswitch.transfer.api.impl.GsonSerializer;
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

    public Object send(TransferRequest tr) throws Exception {
        
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
        
        if(response instanceof HashMap) {
            
            String responseCode = response.get(Interswitch.RESPONSE_CODE);
            String msg = response.get(Interswitch.RESPONSE_MESSAGE);
            
            if(responseCode.equals("200")) {
                
            }
            else if(responseCode.startsWith("4")){
                
            }
            else if(responseCode.startsWith("5")){
                
            }
            else {
                //should never happen
            }
            
        }
        return response;
    }

    @Override
    public Object send(Object obj) {
        // TODO Auto-generated method stub
        return null;
    }

    


}
