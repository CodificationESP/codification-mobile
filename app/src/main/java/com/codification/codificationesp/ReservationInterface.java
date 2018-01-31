package com.codification.codificationesp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Arame on 28/01/2018.
 */

public interface ReservationInterface {
    @GET("reservations/")
    Call<List<Reservation>> getReservations();

    @POST("reservations/")
    Call<Token> reserver(@Body Reservation reservation);


}
