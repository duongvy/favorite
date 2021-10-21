package com.example.appmusic.api.Album;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlbumMethods {

    @GET("/api/albums/{id}")
    Call<AlbumResponse> getById(@Path("id") String id);
}
