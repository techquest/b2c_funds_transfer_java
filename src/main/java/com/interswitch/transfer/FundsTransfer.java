package com.interswitch.transfer;

import java.util.HashMap;

import com.google.gson.Gson;
import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.api.Validator;
import com.interswitch.transfer.api.impl.GsonSerializer;
import com.interswitch.transfer.codec.AccountValidation;
import com.interswitch.transfer.codec.BankResponse;
import com.interswitch.transfer.codec.TransferResponse;
import com.interswitch.transfer.utility.Utility;
import com.interswitch.transfer.validation.FundTransferValidator;

public class FundsTransfer implements Transfer, FetchBanks {

    public static Interswitch interswitch;

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

    public FundsTransfer(String clientId, String clientSecret, String env) {
        interswitch = new Interswitch(clientId, clientSecret, env);
        this.codec = new GsonSerializer();
        this.validator = new FundTransferValidator();

    }
    public FundsTransfer(String clientId, String clientSecret) {
        this(clientId, clientSecret, Interswitch.ENV_SANDBOX);
    }

    public TransferResponse send(TransferRequest tr) throws Exception {

        validator.validate(tr);

        String mac = Utility.generateMAC(tr);

        tr.setMac(mac);

        String json = codec.marshall(tr);

        System.out.println(json);

        HashMap<String, String> extraHeaders = new HashMap<String, String>();
        HashMap<String, String> response = null;
        try {
            response = interswitch.send(Constants.TRANSFER_RESOURCE_URL, Constants.POST, json, extraHeaders);
        } catch (Exception ex) {
            // connection error,
            throw ex;
        }

        TransferResponse resp = new TransferResponse();

        if (response instanceof HashMap) {

            String responseCode = response.get(Interswitch.RESPONSE_CODE);
            String msg = response.get(Interswitch.RESPONSE_MESSAGE);
            Gson g = new Gson();
            resp = g.fromJson(msg, TransferResponse.class);

            // verify resp here

        }
        return resp;
    }

    public BankResponse fetchBanks() throws Exception {

        HashMap<String, String> extraHeaders = new HashMap<String, String>();
        HashMap<String, String> response = interswitch.send(Constants.GET_ALL_BANKS_RESOURCE_URL, Constants.GET, "", extraHeaders);

        String responseCode = response.get(Interswitch.RESPONSE_CODE);
        String msg = response.get(Interswitch.RESPONSE_MESSAGE);
        Gson g = new Gson();
        BankResponse resp = g.fromJson(msg, BankResponse.class);

        return resp;

    }

    public AccountValidation validateAccount(TransferRequest request) throws Exception {

        /// api/v1/nameenquiry/banks/076/accounts/3011747903
        String bankCode = request.getBankCode();
        String accountNumber = request.getAccountNumber();
        HashMap<String, String> extraHeaders = new HashMap<String, String>();
        String url = Constants.ACCOUNT_VALIDATION_URL_PREFIX + bankCode + "/" + Constants.ACCOUNT_VALIDATION_URL_SUFFIX + accountNumber + "/names";
        HashMap<String, String> response = interswitch.send(url, Constants.GET, "", extraHeaders);

        String responseCode = response.get(Interswitch.RESPONSE_CODE);
        String msg = response.get(Interswitch.RESPONSE_MESSAGE);
        Gson g = new Gson();
        AccountValidation resp = g.fromJson(msg, AccountValidation.class);

        return resp;
    }

    public Object send(Object obj) {
        // TODO Auto-generated method stub
        return null;
    }

}
