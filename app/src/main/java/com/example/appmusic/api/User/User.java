package com.example.appmusic.api.User;

import java.io.Serializable;

public class User implements Serializable {
    private String profilePictureUrl;

    private String createdAt;

    private String _id;

    private String fullname;

    private String id;

    private String email;

    private String username;

    private String updatedAt;

    public String getProfilePictureUrl ()
    {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl (String profilePictureUrl)
    {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getFullname ()
    {
        return fullname;
    }

    public void setFullname (String fullname)
    {
        this.fullname = fullname;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [profilePictureUrl = "+profilePictureUrl+", createdAt = "+createdAt+", _id = "+_id+", fullname = "+fullname+", id = "+id+", email = "+email+", username = "+username+", updatedAt = "+updatedAt+"]";
    }
}
