package com.soberdriver.client.soberdriver.services.chat_service.models;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("count")
    private Integer count;
    @SerializedName("from")
    private Object from;
    @SerializedName("total")
    private Integer total;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
