package com.example.appmusic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmusic.api.ApiClient;
import com.example.appmusic.api.Login.UserResponse;
import com.example.appmusic.api.User.GetMeResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = findViewById(R.id.textView);

        Call<GetMeResponse> getMe = ApiClient.getMe().getMe();
        getMe.enqueue(new Callback<GetMeResponse>() {
            @Override
            public void onResponse(Call<GetMeResponse> call, Response<GetMeResponse> response) {
                Toast.makeText(Home.this, "get Me Successfully!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GetMeResponse> call, Throwable t) {

            }
        });

    }
}