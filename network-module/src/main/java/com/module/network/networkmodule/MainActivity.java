package com.module.network.networkmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.module.network.networkmodule.api_v1.HttpService;


import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Network module test";
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = "89653502956";
//
//        HttpService.getInstance()
//                .getPinCode(phone)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });

//        HttpService.getInstance()
//                .getAuthKey(phone, 2222)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<AuthKey>() {
//                    @Override
//                    public void call(AuthKey authKey) {
//                        PhoneUtil.savePhone(MainActivity.this, phone);
//                        UserTokenUtil.saveToken(MainActivity.this, authKey.getAuthKey());
////                        createRequestsWithAuth();
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });
        createRequestsWithAuth();
    }

    private void createRequestsWithAuth() {
//        HttpService.getInstance()
//                .createNewUser(this, "123123131", "test")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });
//        HttpService.getInstance()
//                .createNewUser(this, "23424232", "test12")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });
//        HttpService.getInstance()
//                .createNewUser(this, "12312321321", "test3")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });
//
//        HttpService.getInstance()
//                .getAllUsers(this)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });

//        Address address = new Address();
//        address.addCenter(55.7522200f);
//        address.addCenter(37.6155600f);
////        Date date = new Date();
//        Order order = new Order(address, null);
//        order.setDestination(address);
//        order.setAsSoonAsPossible(true);
//        order.setClientPhone(phone);
//        order.setDriverDetails(new DriverDetails());
//        List<Order> orders = new ArrayList<>();
//        orders.add(order);
//
//
//        HttpService.getInstance()
//                .createUserOrderList(this, orders)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });
//
////
        HttpService.getInstance()
                .pickOrder(this, "58471c71148df41672e7bb4d")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
//
//
//        HttpService.getInstance()
//                .getUserActiveOrders(this)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });

//        HttpService.getInstance()
//                .getUserActiveOrders(this)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });
    }

}
