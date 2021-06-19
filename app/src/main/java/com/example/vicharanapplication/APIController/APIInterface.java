package com.example.vicharanapplication.APIController;

import com.example.vicharanapplication.Requests.LogInRequest;
import com.example.vicharanapplication.Responses.LogInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/login")
    Call<LogInResponse>userLogin(@Body LogInRequest logInRequest);

}
