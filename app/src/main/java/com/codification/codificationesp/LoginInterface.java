package com.codification.codificationesp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Arame on 21/01/2018.
 */

public interface LoginInterface {
    @POST("utilisateurs/login?include={\"user\" : \"etudiant\"")
    Call<Token> login(@Body Credentials creds);

    @POST("utilisateurs/logout?include=user")
    Call<Token> logout(@Body Credentials creds);

    @GET("utilisateurs/{id}")
    Call<User> getUserDetails(@Path("id") String id, @Header("Authorization") String token);

}
