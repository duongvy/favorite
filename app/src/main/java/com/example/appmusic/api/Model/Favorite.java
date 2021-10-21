package com.example.appmusic.api.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Favorite implements Serializable {
    private User user;
    private String _id;
    private ArrayList<Song> songs;


    public User getUser() {
        return user;
    }
    public String get_id() {
        return _id;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
