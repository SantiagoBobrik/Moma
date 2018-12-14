package com.example.bobrik.santiagodiazmuseo.Dao;

import com.example.bobrik.santiagodiazmuseo.Model.ContenedorPintura;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicePintura {

    @GET("bins/x858r")
    Call<ContenedorPintura> getPintura();
}
