package com.example.vicharanapplication.APIController;

import com.example.vicharanapplication.Requests.LogInRequest;
import com.example.vicharanapplication.Responses.LogInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/VichranApp/login")
    Call<LogInResponse>userLogin(@Body LogInRequest logInRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/VichranApp/Home")
    Call<LogInResponse>getUser(@Header("Authorization") String auth);

}
