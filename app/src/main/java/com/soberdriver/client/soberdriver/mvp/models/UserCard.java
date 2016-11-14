package com.soberdriver.client.soberdriver.mvp.models;

/**
 * Created by zest .
 */

public class UserCard {
    private String cardName;

    public UserCard(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
