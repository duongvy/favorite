package com.example.appmusic.api.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMe {
    @GET("/api/auth/getMe")
    Call<GetMeResponse> getMe();
}
