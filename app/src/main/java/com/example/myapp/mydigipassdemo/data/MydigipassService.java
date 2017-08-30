package com.example.myapp.mydigipassdemo.data;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MydigipassService {
    @FormUrlEncoded
    @POST("token")
    Call<Map<String, Object>> token(@Field("code") String code, @Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("redirect_uri") String redirectUri, @Field("grant_type") String grantType);

    @GET("user_data")
    Call<Map<String, Object>>  getUserData(@Header("Authorization") String token);
}
