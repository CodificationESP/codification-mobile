package com.codification.codificationesp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Arame on 28/01/2018.
 */

public interface CodificationInterface {
    @GET("codifications/")
    Call<List<Codification>> getCodifications();

    @POST("codifications/")
    Call<Token> codifier(@Body Codification codification);
}
