package com.codification.codificationesp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Arame on 28/01/2018.
 */

public interface EtudiantInterface {
    @GET("etudiants/{id}/codification")
    Call<Codification> getCodificationEtudiant(@Path("id") String id);

    @GET("etudiants/{id}?filter={\"include\" : { \"reservation\": \"chambre\" }}")
    Call<Etudiant> getEtudiantAvecReservation(@Path("id") String id);

    @POST("utilisateurs/")
    Call<User> creerutilisateur(@Body User user);

    @POST("etudiants/")
    Call<Etudiant> creeretudiant(@Body Etudiant etud);

    @POST("etudiants/{id}/codification")
    Call<Etudiant> codifieretudiant(@Path("id") String id, @Body Codification codification);

    @POST("etudiants/{id}/reservation")
    Call<Reservation> reserveretudiant(@Path("id") String id, @Body Reservation reservation);

    @GET("utilisateurs/{id}/etudiant")
    Call<Etudiant> getetudiant(@Path("id") String id);
}
