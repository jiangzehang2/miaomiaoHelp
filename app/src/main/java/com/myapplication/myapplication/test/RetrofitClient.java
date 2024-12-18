package com.myapplication.myapplication.test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static String url = "http://172.26.175.103:8080/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url) // 设置 Flask 服务的地址
                    .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 转换器
                    .build();
        }
        return retrofit;
    }
}

