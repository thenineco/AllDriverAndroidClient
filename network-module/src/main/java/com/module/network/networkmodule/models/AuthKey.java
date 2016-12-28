package com.module.network.networkmodule.models;

/**
 * Created by zestxx
 */

public class AuthKey {


    private String authKey;

    public AuthKey(String authKey) {
        this.authKey = authKey;
    }

    @Override
    public String toString() {
        return authKey;
    }

    public String getAuthKey() {
        return authKey;
    }

}
