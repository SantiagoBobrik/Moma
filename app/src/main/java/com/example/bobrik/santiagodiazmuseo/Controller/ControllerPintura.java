package com.example.bobrik.santiagodiazmuseo.Controller;

import android.content.Context;

import com.example.bobrik.santiagodiazmuseo.Dao.DaoIntenerPintura;
import com.example.bobrik.santiagodiazmuseo.Model.Pintura;
import com.example.bobrik.santiagodiazmuseo.Utils.ResultListener;
import com.example.bobrik.santiagodiazmuseo.Utils.Util;

import java.util.List;

public class ControllerPintura {


    public void traerPintura(Context context, final ResultListener<List<Pintura>> listenerView) {
        if (Util.isOnline(context)) {
            DaoIntenerPintura daoIntenerPintura = new DaoIntenerPintura();
            daoIntenerPintura.traerPintura(context, new ResultListener<List<Pintura>>() {
                @Override
                public void finish(List<Pintura> resultado) {
                    listenerView.finish(resultado);
                }
            });
        } else {
            // BASE DE DATOS
        }
    }
}
