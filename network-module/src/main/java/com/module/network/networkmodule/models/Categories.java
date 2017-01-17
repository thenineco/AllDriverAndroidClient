package com.module.network.networkmodule.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roman
 */
public class Categories {
    private Map<String, Integer> categories = new HashMap<>(4);

    public Categories() {
        categories.put("A", 0);
        categories.put("B", 0);
        categories.put("C", 0);
        categories.put("D", 0);
        categories.put("E", 0);
    }

    public void changeCategoryA(int status) {
        categories.put("A", status);
    }

    public void changeCategoryB(int status) {
        categories.put("B", status);
    }

    public void changeCategoryC(int status) {
        categories.put("C", status);
    }

    public void changeCategoryD(int status) {
        categories.put("D", status);
    }

    public void changeCategoryE(int status) {
        categories.put("E", status);
    }
}
