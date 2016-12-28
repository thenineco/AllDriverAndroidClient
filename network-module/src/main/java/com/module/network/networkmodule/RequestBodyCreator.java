package com.module.network.networkmodule;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by roman
 */

public class RequestBodyCreator {
    private HashMap<String, Object> requestsMap;


    public RequestBodyCreator() {
        requestsMap = new HashMap<>();
    }

    public RequestBodyCreator(Context context) {
        requestsMap = new HashMap<>();
        addAuthDataToRequestBody(context);
    }

    private void addAuthDataToRequestBody(Context context) {
        requestsMap.put("phone", PhoneUtil.getPhone(context));
        requestsMap.put("authKey", TokenUtil.getToken(context));
    }


    public RequestBodyCreator addParam(String paramName, Object paramValue) {
        requestsMap.put(paramName, paramValue);
        return this;
    }

    public HashMap<String, Object> getBody() {
        return requestsMap;
    }
}
