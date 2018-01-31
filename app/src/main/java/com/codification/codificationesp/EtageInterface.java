package com.codification.codificationesp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Arame on 27/01/2018.
 */

public interface EtageInterface {
    @GET("etages/")
    Call<List<Batiment>> getEtages();

    @GET("etages/{id}/couloirs")
    Call<List<Couloir>> getCouloirsEtage(@Path("id") String id);

    @GET("etages/{id}/contraintes")
    Call<List<Contrainte>> getContraintesEtage(@Path("id") String id);
}
