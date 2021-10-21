package com.example.appmusic.api.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String fullname;
    private String username;
    private String profilePictureUrl;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}
