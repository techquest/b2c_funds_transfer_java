package com.interswitch.transfer;

public class FundTransfer implements Transfer {
    
    private String initiatingEntityCode;
    
    private static FundTransfer instance;
    
    private FundTransfer(){
        
    }
    
    public FundTransfer getInstance(String initiatingEntityCode){
        if(!(instance instanceof FundTransfer)) {
            instance = new FundTransfer();
            instance.initiatingEntityCode = initiatingEntityCode;
        }
        return instance;
    }

    public String getInitiatingEntityCode() {
        return initiatingEntityCode;
    }

    public Object send() {
        // TODO Auto-generated method stub
        return null;
    }

}
