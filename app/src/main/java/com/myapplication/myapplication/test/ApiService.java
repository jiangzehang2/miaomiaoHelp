package com.myapplication.myapplication.test;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {
   /* @GET("api/v1/success")
    Call<SuccessResponse> getSuccessMessage();*/

    //登录接口
    @GET("api/login")
    Call<LoginResponse> login(
            @Query("name") String phoneNumber,
            @Query("password") String password
    );

    //注册接口
    @GET("api/register")
    Call<RegisterResponse> register(
            @Query("phoneNumber") String phoneNumber,
            @Query("password") String password
    );

    //获取细节信息
    @GET("api/getUserDetails")
    Call<UserDetailsResponse> getUserDetails(
            @Query("phoneNumber") String phoneNumber
    );


    //修改信息
    @GET("api/update")
    Call<UpdateResponse> update(
            @Query("name") String name,
            @Query("phoneNumber") String phoneNumber,
            @Query("password") String password
    );

    //选择充值金额
    @GET("api/moneyoption")
    Call<MoneyOptionResponse>moneyoption(
            @Query("option")String option
    );


}