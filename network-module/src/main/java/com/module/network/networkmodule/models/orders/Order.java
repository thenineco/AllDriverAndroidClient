package com.module.network.networkmodule.models.orders;

import com.google.gson.annotations.SerializedName;
import com.module.network.networkmodule.models.address.Address;
import com.module.network.networkmodule.models.driver.Driver;
import com.module.network.networkmodule.models.driver.DriverDetails;

import java.util.Date;
import java.util.List;

/**
 * Created by zestxx
 */

public class Order {

    @SerializedName("_id")
    private String orderId;
    @SerializedName("status")
    private int status;
    @SerializedName("userId")
    private String userId;
    @SerializedName("location")
    private Address location;
    @SerializedName("asSoonAsPossible")
    private boolean asSoonAsPossible;
    @SerializedName("orderDate")
    private Date orderDate;
    @SerializedName("drivers")
    private List<Driver> mDriverList;

    private String driverId;
    private Date added;
    private Address destination;
    private String clientPhone;
    private DriverDetails driverDetails;
    private Options options;

    public Order(Address location, Date orderDate) {
        this.location = location;
        this.orderDate = orderDate;
    }

    public List<Driver> getDriverList() {
        return mDriverList;
    }

    public void setDriverList(
            List<Driver> driverList) {
        mDriverList = driverList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public boolean isAsSoonAsPossible() {
        return asSoonAsPossible;
    }

    public void setAsSoonAsPossible(boolean asSoonAsPossible) {
        this.asSoonAsPossible = asSoonAsPossible;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public DriverDetails getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(DriverDetails driverDetails) {
        this.driverDetails = driverDetails;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public Address getDestination() {
        return destination;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }
}
