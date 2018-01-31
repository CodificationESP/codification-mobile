package com.codification.codificationesp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Arame on 27/01/2018.
 */

public interface ChambreInterface {
    @GET("Chambres/")
    Call<List<Chambre>> getChambres();

    @GET("Chambres/{id}")
    Call<Chambre> getChambre(@Path("id") int id);

    @GET("chambres/{id}/codification")
    Call<List<Codification>> getCodificationChambre(@Path("id") String id);

    @GET("chambres/{id}/reservation")
    Call<List<Reservation>> getReservationChambre(@Path("id") String id);
}
