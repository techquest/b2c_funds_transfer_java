package com.interswitch.transfer;

import com.interswitch.transfer.codec.BankResponse;

public interface FetchBanks {
    
    BankResponse fetchBanks() throws Exception;
}
