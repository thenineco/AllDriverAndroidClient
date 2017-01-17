package com.module.network.networkmodule.api_v1;

import com.module.network.networkmodule.models.AuthKey;
import com.module.network.networkmodule.models.driver.DriverDetails;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zest .
 */

public class HttpRequests {


    interface Authentication {

        @POST("/authentication/authenticate")
        Observable<AuthKey> getAuthKey(@Body HashMap<String, Object> body);

        @GET("/authentication/requestCode/{phone}")
        Observable<ResponseBody> getPinCode(@Path("phone") String phones);
    }

    interface Drivers {
        @POST("/drivers")
        Observable<ResponseBody> createNewDriver(@Body HashMap<String, Object> queryMap);

        @GET("/drivers/{driverId}")
        Observable<ResponseBody> getDriverById(@Path("driverId") String orderId,
                @QueryMap HashMap<String, Object> queryMap);

        @PATCH("/drivers/sessions/open")
        Observable<ResponseBody> openDriverSession(@Body HashMap<String, Object> queryMap);

        @PATCH("/drivers/sessions/close")
        Observable<ResponseBody> closeDriverSession(@Body HashMap<String, Object> queryMap);

        @GET("/drivers/preferences/users")
        Observable<ResponseBody> getDriversLikeToUser(
                @QueryMap HashMap<String, Object> queryMap);

        @POST("/drivers/preferences/users")
        Observable<ResponseBody> addDriversLikeToUser(@Body HashMap<String, Object> queryMap);
    }

    interface Addresses {
        @GET("/users/preferences/addresses")
        Observable<ResponseBody> getAddresses(@QueryMap HashMap<String, Object> queryMap);

        @PUT("/users/preferences/addresses")
        Observable<ResponseBody> addAddresses(@Body HashMap<String, Object> queryMap);
    }

    interface Users {
        @GET("/users")
        Observable<ResponseBody> getUser(@QueryMap HashMap<String, Object> queryMap);

        @POST("/users")
        Observable<ResponseBody> createNewUser(@Body HashMap<String, Object> queryMap);

        @PATCH("/users")
        Observable<ResponseBody> editUser(@Body HashMap<String, Object> queryMap);

        @GET("/users/all")
        Observable<ResponseBody> getAllUsers(@QueryMap HashMap<String, Object> queryMap);

        @GET("/users/preferences/drivers")
        Observable<ResponseBody> getUserLikeToDrivers(
                @QueryMap HashMap<String, Object> queryMap);

        @POST("/users/preferences/drivers")
        Observable<ResponseBody> addUserLikeToDrivers(@Body HashMap<String, Object> queryMap);

    }

    interface Orders {
        @GET("/orders")
        Observable<ResponseBody> getUserActiveOrders(@QueryMap HashMap<String, Object> queryMap);

        @POST("/orders")
        Observable<ResponseBody> createUserOrderList(@Body HashMap<String, Object> queryMap);

        @GET("/orders/all")
        Observable<ResponseBody> getAllOrdersByPeriod(@QueryMap HashMap<String, Object> queryMap);

        @PATCH("/orders/cancel/{orderId}")
        Observable<ResponseBody> cancelOrder(@Path("orderId") String orderId,
                @Body HashMap<String, Object> queryMap);

        @PATCH("/orders/pick/{orderId}")
        Observable<ResponseBody> pickOrder(@Path("orderId") String orderId,
                @Body HashMap<String, Object> queryMap);

        @GET("/orders/drivers")
        Observable<ResponseBody> getOrderDrivers(@QueryMap HashMap<String, Object> queryMap);

        @PATCH("/orders/drivers")
        Observable<ResponseBody> callDriversForDriver(@Body HashMap<String, Object> queryMap);

        @GET("/orders/active")
        Observable<ResponseBody> getActiveOrders(@QueryMap HashMap<String, Object> queryMap);

        @GET("/orders/route/price")
        Observable<ResponseBody> getOrderPrice(@QueryMap HashMap<String, Object> queryMap);
    }

//    interface TokenRequests {
//        @POST(API_PREFIX + "/token")
//        Observable<Token> getToken(@Query("code") String verifyCod,
//                @Query("user_id") String userId);
//    }
}
