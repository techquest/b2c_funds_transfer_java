package com.interswitch.transfer.api;

public interface JsonSerializer {

    String marshall(Object obj);
    Object unmarshall(String str, Object obj);
}
