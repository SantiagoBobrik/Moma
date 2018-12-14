package com.example.bobrik.santiagodiazmuseo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bobrik.santiagodiazmuseo.Controller.ControllerPintura;
import com.example.bobrik.santiagodiazmuseo.Model.ContenedorPintura;
import com.example.bobrik.santiagodiazmuseo.Model.Pintura;
import com.example.bobrik.santiagodiazmuseo.R;
import com.example.bobrik.santiagodiazmuseo.Utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterPintura.Comunicacion {

    private List<Pintura> datos = new ArrayList<>();
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AdapterPintura adapterPintura = new AdapterPintura(datos,this);
        final ControllerPintura controllerPintura = new ControllerPintura();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapterPintura);


         controllerPintura.traerPintura(this, new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
                // DATOS
                datos = resultado;
                adapterPintura.setPinturaList(datos);
            }
        });



         button = findViewById(R.id.botonuser);


         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(MainActivity.this, PanelUser.class);
                 startActivity(intent);

             }
         });



    }
    @Override
    public void irDetalle(Pintura pintura){

        Intent intent = new Intent(MainActivity.this, Detalle.class);

        Bundle bundle = new Bundle();
        bundle.putString(Detalle.KEY_ARTISTID, pintura.getArtistId());
        bundle.putString(Detalle.KEY_NAME, pintura.getName());
        bundle.putString(Detalle.KEY_IMAGEN, pintura.getImage());




        intent.putExtras(bundle);
        startActivity(intent);
    }
}
