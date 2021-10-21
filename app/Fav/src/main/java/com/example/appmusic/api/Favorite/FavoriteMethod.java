package com.example.appmusic.api.Favorite;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavoriteMethod {

    @GET("/api/favorites/me")
    Call<FavoriteResponse> getSongInFavorite();

    @POST("/api/favorites/me/{id}")
    Call<FavoriteResponse> addSongToFavorite(@Path("id") String songId);

    @DELETE("/api/favorites/me/{id}")
    Call<FavoriteResponse> removeSongFromFavorite(@Path("id") String songId);

    @GET("/api/favorites/me/{id}")
    Call<FavoriteCheckerResponse> checkIfExist(@Path("id") String songId);

}
