package com.interswitch.transfer.api.impl;

import com.google.gson.Gson;
import com.interswitch.transfer.api.JsonSerializer;

public class GsonSerializer implements JsonSerializer {
    
    private Gson g;
    
    public GsonSerializer(){
        g = new Gson();
    }

    public String marshall(Object obj) {
        return g.toJson(obj);
    }

    public Object unmarshall(String str, Object obj) {
        return g.fromJson(str, obj.getClass());
    }

   

}
