package com.codification.codificationesp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arame on 21/01/2018.
 */

public class User {
    @SerializedName("username")
    String login;

    @SerializedName("password")
    String motdepasse;

    @SerializedName("email")
    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("id")
    private transient int id;


    public User(String login, String motdepasse, String email) {
        this.login = login;
        this.motdepasse = motdepasse;
        this.email = email;
    }
}
