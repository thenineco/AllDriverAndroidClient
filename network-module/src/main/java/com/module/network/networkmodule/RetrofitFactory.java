package com.module.network.networkmodule;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zest
 */
public class RetrofitFactory {

    private static Retrofit retrofit;
    private static OkHttpClient client;

    public <T> T getInstance(Class<T> reqClass) {
        if (client == null) {
            client = new OkHttpClient().newBuilder().readTimeout(NetworkConfig.READ_TIMEOUT,
                    TimeUnit.SECONDS)
                    .connectTimeout(NetworkConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            // Request customization: add request headers
                            Request.Builder requestBuilder = original.newBuilder()
                                    .header("Accept", "application/json");
                            Request request = requestBuilder.build();
                            return chain.proceed(request);

                        }
                    })
                    .writeTimeout(NetworkConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();

        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(NetworkConfig.HOST)
                    .client(client)
                    .build();
        }
        return retrofit.create(reqClass);
    }
}
