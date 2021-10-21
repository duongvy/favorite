package com.example.appmusic.api.User;

import java.io.Serializable;

public class GetMeResponse implements  Serializable {
    public class Data implements Serializable
    {
        private User currentUser;

        public User getCurrentUser ()
        {
            return currentUser;
        }

        public void setCurrentUser (User currentUser)
        {
            this.currentUser = currentUser;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [currentUser = "+currentUser+"]";
        }
    }

    private Data data;

    private String status;

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
}
