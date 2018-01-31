package com.codification.codificationesp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Arame on 27/01/2018.
 */

public interface CouloirInterface {
    @GET("couloirs/")
    Call<List<Couloir>> getCouloirs();

    @GET("couloirs/{id}/chambres")
    Call<List<Chambre>> getChambresCouloir(@Path("id") String id);

    @GET("couloirs/{id}/contraintes")
    Call<List<Contrainte>> getContraintesCouloir(@Path("id") String id);
}
