package com.example.appmusic.api.Register;

import java.io.Serializable;

public class RegisterResponse implements Serializable {

    public class Data implements Serializable{
        private String access_token;

        public String getAccess_token ()
        {
            return access_token;
        }

        public void setAccess_token (String access_token)
        {
            this.access_token = access_token;
        }
    }

    private Data data;

    private String status;

    public Data getData ()
    {
        return data;
    }

    public void setData(Data data) {
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

}
