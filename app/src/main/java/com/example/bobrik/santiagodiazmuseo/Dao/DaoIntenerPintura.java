package com.example.bobrik.santiagodiazmuseo.Dao;

import android.content.Context;
import android.widget.Toast;

import com.example.bobrik.santiagodiazmuseo.Model.ContenedorPintura;
import com.example.bobrik.santiagodiazmuseo.Model.Pintura;
import com.example.bobrik.santiagodiazmuseo.Utils.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaoIntenerPintura extends DaoHelper {
    public static final String BASE_URL = "https://api.myjson.com/";

    private ServicePintura servicePintura;


    public DaoIntenerPintura() {
        super(BASE_URL);
        this.servicePintura = retrofit.create(ServicePintura.class);
    }

    public void traerPintura(final Context context, final ResultListener<List<Pintura>> listenerController) {

        Call<ContenedorPintura> call = servicePintura.getPintura();

        call.enqueue(new Callback<ContenedorPintura>() {
            @Override
            public void onResponse(Call<ContenedorPintura> call, Response<ContenedorPintura> response) {



                if (response.code() >= 200 && response.code() < 300) {
                    ContenedorPintura contenedorPintura = response.body();
                    listenerController.finish(contenedorPintura.getpaints());
                }


            }

            @Override
            public void onFailure(Call<ContenedorPintura> call, Throwable t) {
                //TODO Completar


            }
        });
    }
}
