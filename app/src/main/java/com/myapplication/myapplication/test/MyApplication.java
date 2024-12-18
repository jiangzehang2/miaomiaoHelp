package com.myapplication.myapplication.test;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyApplication extends Application {
  /*@Override
    public void onCreate() {
        super.onCreate();

        // 获取 Retrofit 实例并创建 ApiService 实例
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // 发起 GET 请求
        Call<SuccessResponse> call = apiService.getSuccessMessage();

        // 发送请求并处理响应
        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                if (response.isSuccessful()) {
                    // 请求成功
                    SuccessResponse s = response.body();
                    if (s != null) {
                        Toast.makeText(MyApplication.this, "收到信息： " + s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 请求失败
                    Toast.makeText(MyApplication.this, "请求失败: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.d("QQHH", "请求失败: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                // 请求失败
                Toast.makeText(MyApplication.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("QQHH", "请求失败: " + t.getMessage());
            }
        });


    }*/
}
