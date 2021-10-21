package com.example.appmusic.api.Album;

import com.example.appmusic.api.Model.Album;

import java.util.Map;

public class AlbumResponse {
    private String status;
    private Map<String, Album> data;

    public String getStatus() {
        return status;
    }

    public Map<String, Album> getData() {
        return data;
    }
}
