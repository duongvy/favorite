package com.example.appmusic.api.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Album implements Serializable {
    private String _id;
    private String name;
    private String imageUrl;
    private Song mainSong;
    private ArrayList<Song> songs;
    private Date releaseDate;

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Song getMainSong() {
        return mainSong;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
