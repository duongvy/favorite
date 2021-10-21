package com.example.appmusic.api.Favorite;

import com.example.appmusic.api.Model.Favorite;

import java.util.Map;

public class FavoriteResponse {
    private String status;
    private Map<String, Favorite> data;

    public String getStatus() {
        return status;
    }

    public Map<String, Favorite> getData() {
        return data;
    }
}
