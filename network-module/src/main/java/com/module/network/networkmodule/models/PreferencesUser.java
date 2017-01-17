package com.module.network.networkmodule.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zest
 */

public class PreferencesUser {

    @SerializedName("mark")
    @Expose
    private Integer mark;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("msg")
    private List<PreferencesUser> mPreferencesList;

    public List<PreferencesUser> getPreferencesList() {
        return mPreferencesList;
    }

    public void setPreferencesList(
            List<PreferencesUser> preferencesList) {
        mPreferencesList = preferencesList;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
