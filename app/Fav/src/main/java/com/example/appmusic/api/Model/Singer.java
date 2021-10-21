package com.example.appmusic.api.Model;

import java.io.Serializable;

public class Singer implements Serializable {
    private String _id;
    private String name;
    private Song songs;
    private String imageUrl;

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public Song getSongs() {
        return songs;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
