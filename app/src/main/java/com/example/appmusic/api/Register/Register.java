package com.example.appmusic.api.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Register {

    @POST("/api/auth/register")
    Call<RegisterResponse> saveUser(@Body RegisterRequest registerRequest);


}
