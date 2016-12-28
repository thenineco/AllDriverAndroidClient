package com.module.network.networkmodule.api_v1;

import android.content.Context;

import com.module.network.networkmodule.RetrofitFactory;
import com.module.network.networkmodule.models.Address;
import com.module.network.networkmodule.models.AuthKey;
import com.module.network.networkmodule.models.orders.DriverDetails;
import com.module.network.networkmodule.RequestBodyCreator;
import com.module.network.networkmodule.models.orders.Order;

import org.xmlpull.v1.sax2.Driver;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by zest .
 */

public class HttpService {

    private static HttpService httpService;
    private RetrofitFactory retrofitFactory;

    public HttpService() {
        this.retrofitFactory = new RetrofitFactory();
    }

    public static HttpService getInstance() {
        if (httpService == null) {
            httpService = new HttpService();
        }
        return httpService;
    }

    /******************************************************************************************
     * Authentication
     *****************************************************************************************/
    public Observable<AuthKey> getAuthKey(String phone, int pinCode) {
        HttpRequests.Authentication request = retrofitFactory.getInstance(
                HttpRequests.Authentication.class);

        RequestBodyCreator requestBodyCreator = new RequestBodyCreator();
        requestBodyCreator.addParam("phone", phone);
        requestBodyCreator.addParam("pincode", pinCode);

        return request.getAuthKey(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> getPinCode(String phone) {
        HttpRequests.Authentication request = retrofitFactory.getInstance(
                HttpRequests.Authentication.class);
        return request.getPinCode(phone)
                .subscribeOn(Schedulers.newThread());
    }

    /******************************************************************************************
     * Drivers
     ***************************************************************************************/

    public Observable<List<DriverDetails>> getDriversWithUserPreference(Context context,
            String userId,
            String driverId,
            int mark, String comment) {

        HttpRequests.Drivers request = retrofitFactory.getInstance(
                HttpRequests.Drivers.class);

        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        requestBodyCreator.addParam("userId", userId);
        requestBodyCreator.addParam("driverId", driverId);
        requestBodyCreator.addParam("mark", mark);
        requestBodyCreator.addParam("comment", comment);

        HashMap<String, Object> requestBody = requestBodyCreator.getBody();

        return request.getDriversWithUserPreference(requestBody)
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> addDriversWithUserPreference(Context context, String userId,
            String driverId,
            int mark, String comment) {

        HttpRequests.Drivers request = retrofitFactory.getInstance(
                HttpRequests.Drivers.class);

        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        requestBodyCreator.addParam("userId", userId);
        requestBodyCreator.addParam("driverId", driverId);
        requestBodyCreator.addParam("mark", mark);
        requestBodyCreator.addParam("comment", comment);

        HashMap<String, Object> requestBody = requestBodyCreator.getBody();

        return request.addUserLikeToDrivers(requestBody)
                .subscribeOn(Schedulers.newThread());
    }

    /******************************************************************************************
     * Addresses
     ****************************************************************************************/
    public Observable<ResponseBody> getAddresses(Context context) {
        HttpRequests.Addresses request = retrofitFactory.getInstance(
                HttpRequests.Addresses.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);
        return request.getAddresses(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> addAddress(Context context, List<Address> addresses) {
        HttpRequests.Addresses request = retrofitFactory.getInstance(
                HttpRequests.Addresses.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        requestBodyCreator.addParam("addresses", addresses);

        return request.getAddresses(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    /*****************************************************************************************
     * Users
     **************************************************************************************/

    public Observable<ResponseBody> getUser(Context context) {
        HttpRequests.Users request = retrofitFactory.getInstance(
                HttpRequests.Users.class);

        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        return request.getUser(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> getAllUsers(Context context) {
        HttpRequests.Users request = retrofitFactory.getInstance(
                HttpRequests.Users.class);

        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        return request.getAllUsers(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> addNewUser(Context context, String phone, String name) {
        HttpRequests.Users request = retrofitFactory.getInstance(
                HttpRequests.Users.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        requestBodyCreator.addParam("phone", phone);
        requestBodyCreator.addParam("name", name);

        return request.addNewUser(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> editUser(Context context, String phone, Object changes) {
        HttpRequests.Users request = retrofitFactory.getInstance(
                HttpRequests.Users.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        requestBodyCreator.addParam("phone", phone);
        requestBodyCreator.addParam("changes", changes);

        return request.editUser(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    /******************************************************************************************
     * Orders
     ****************************************************************************************/

    public Observable<ResponseBody> getUserActiveOrders(Context context) {
        HttpRequests.Orders request = retrofitFactory.getInstance(
                HttpRequests.Orders.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        return request.getUserActiveOrders(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> getAllOrdersByPeriod(Date startDate, Date finalDate) {
        HttpRequests.Orders request = retrofitFactory.getInstance(
                HttpRequests.Orders.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator();

        requestBodyCreator.addParam("date1", startDate);
        requestBodyCreator.addParam("date2", finalDate);

        return request.getAllOrdersByPeriod(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> createUserOrderList(Context context, List<Order> orders) {
        HttpRequests.Orders request = retrofitFactory.getInstance(
                HttpRequests.Orders.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);
        requestBodyCreator.addParam("orders", orders);

        return request.createUserOrderList(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> cancelOrder(Context context, String orderId) {
        HttpRequests.Orders request = retrofitFactory.getInstance(
                HttpRequests.Orders.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        return request.cancelOrder(orderId, requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> getOrderDrivers(Context context) {
        HttpRequests.Orders request = retrofitFactory.getInstance(
                HttpRequests.Orders.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        return request.getOrderDrivers(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseBody> callOrderDrivers(Context context, List<Driver> driverList,
            String orderId) {
        HttpRequests.Orders request = retrofitFactory.getInstance(
                HttpRequests.Orders.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);
        requestBodyCreator.addParam("orderId", orderId);
        requestBodyCreator.addParam("drivers", driverList);

        return request.callDriversForDriver(requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }


    public Observable<ResponseBody> pickOrder(Context context, String orderId) {
        HttpRequests.Orders request = retrofitFactory.getInstance(
                HttpRequests.Orders.class);
        RequestBodyCreator requestBodyCreator = new RequestBodyCreator(context);

        return request.pickOrder(orderId, requestBodyCreator.getBody())
                .subscribeOn(Schedulers.newThread());
    }

}


