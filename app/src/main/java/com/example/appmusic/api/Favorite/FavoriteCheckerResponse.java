package com.example.appmusic.api.Favorite;

import java.util.Map;

public class FavoriteCheckerResponse {
    private String status;
    private Map<String, Boolean> data;

    public String getStatus() {
        return status;
    }

    public Map<String, Boolean> getData() {
        return data;
    }
}
