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


}
