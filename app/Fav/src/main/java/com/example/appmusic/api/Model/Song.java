package com.example.appmusic.api.Model;

import java.io.Serializable;
import java.util.Date;

public class Song implements Serializable {
    private String _id;
    private String name;
    private String lyrics;
    private Category category;
    private Date releaseDate;
    private Singer singer;
    private String imageUrl;
    private String songUrl;

    public String getSongUrl() {
        return songUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Singer getSinger() {
        return singer;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public Category getCategory() {
        return category;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
