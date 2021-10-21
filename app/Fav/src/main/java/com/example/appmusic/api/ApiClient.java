package com.example.appmusic.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.appmusic.api.Register.Register;

import com.example.appmusic.api.Login.Login;
import com.example.appmusic.api.User.GetMe;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String token;

    public static String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public static Retrofit getRetrofit(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-musics.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static Register getRegister(){
        Register register = getRetrofit().create(Register.class);

        return register;
    }

    public static Login getUser(){
        Login user = getRetrofit().create(Login.class);

        return user;
    }
    public static GetMe getMe(){
        GetMe user = getRetrofit().create(GetMe.class);

        return user;
    }
}
