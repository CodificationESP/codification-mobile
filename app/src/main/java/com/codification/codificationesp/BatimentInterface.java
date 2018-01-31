package com.codification.codificationesp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Arame on 27/01/2018.
 */

public interface BatimentInterface {
    @GET("batiments?filter={   \"include\": {     \"etages\": {       \"couloirs\": \"chambres\"     }   } }")
    Call<List<Batiment>> getBatiments();

    @GET("batiments/{id}/etages")
    Call<List<Etage>> getEtagesBatiment(@Path("id") String id);

    @GET("batiments/{id}/contraintes")
    Call<List<Contrainte>> getContraintesBatiment(@Path("id") String id);

}
