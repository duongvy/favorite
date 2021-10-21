package com.example.appmusic.api.Login;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Login {
    @POST("/api/auth/login")
    Call<UserResponse> userLogin(@Body UserRequest userRequest);
}
