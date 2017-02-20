package com.interswitch.transfer.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.interswitch.transfer.TransferRequest;
import com.interswitch.transfer.codec.Initiation;
import com.interswitch.transfer.codec.Termination;

public class Utility {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    
    public static String generateMAC(TransferRequest t) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        
        Initiation in = t.getInitiation();
        Termination tm = t.getTermination();
        String init =(in.getAmount()
        +in.getCurrencyCode()
        +in.getPaymentMethodCode()+tm.getTerminationAmount()
        +tm.getTerminationCurrencyCode()
        +tm.getTerminationPaymentMethodCode()
        +tm.getTerminationCountryCode());
        
        
        
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] b = init.getBytes("UTF-8");
        byte[] bytes = md.digest(b);
        
        //do sha512
        
        String output = bytesToHex(bytes);
        
        
        
        return output;
    }
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
