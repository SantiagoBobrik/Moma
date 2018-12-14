package com.example.bobrik.santiagodiazmuseo.View;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bobrik.santiagodiazmuseo.Controller.ControllerPintura;
import com.example.bobrik.santiagodiazmuseo.Model.Artista;
import com.example.bobrik.santiagodiazmuseo.Model.Pintura;
import com.example.bobrik.santiagodiazmuseo.R;
import com.example.bobrik.santiagodiazmuseo.Utils.ResultListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Detalle extends AppCompatActivity {


    public static final String KEY_NAME = "name";
    public static final String KEY_ARTISTID = "artistid";
    public static final String KEY_IMAGEN = "imagen";
    private  String  artistid;


    private ImageView imageView;
    private TextView textView;
    private TextView textViewnombre;
    private TextView textViewInfluencia;
    private TextView textViewNacionalidad;



    private List<Pintura> datos = new ArrayList<>();
    private FirebaseDatabase mDatabase;
    private FirebaseStorage firebaseStorage;
    private StorageReference imagenes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);


        //VISUAL DATOS ARTISTA

        textView = findViewById(R.id.titulo);
        textViewInfluencia = findViewById(R.id.influencia);
        textViewNacionalidad = findViewById(R.id.nacionalidad);
        textViewnombre = findViewById(R.id.nombre);
        imageView = findViewById(R.id.imagen);

        ///////////////////////////////////////////


        //TRAIGO POSICION ADARTER MEDIENTE COMUNICACION
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString(KEY_NAME);
        artistid = bundle.getString(KEY_ARTISTID);
        String imagen = bundle.getString(KEY_IMAGEN);


        //////////////////////////////////////////

        //FIREBASE DATABASE

        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference raiz = mDatabase.getReference();
        DatabaseReference artists = raiz.child("artists");



        ////////////////////////////////////////////////


        //TRAIGO DATOS (PINTURAS) MEDIANTE CONTROLLER



                textView.setText(name);

                firebaseStorage = FirebaseStorage.getInstance();
                StorageReference raizStorge = firebaseStorage.getReference();
                imagenes = raizStorge.child(imagen);
                imagenes.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(imageView.getContext()).load(uri).into(imageView);
                    }
                });




        //////////////////////////////////////////////////////////////////////////

        //FIREBASE STORAGE


        //BUSCO Y SETEO DATOS DESDE FIREBASE DATABASE CON LISTA DE OBRAS
        artists.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {

                    Artista artista = childSnapShot.getValue(Artista.class);


                    if (artista.getArtistId().equals(artistid)) {

                        textViewnombre.setText(artista.getName());
                        textViewInfluencia.setText(artista.getInfluenced_by());
                        textViewNacionalidad.setText(artista.getNationality());


                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        ////////////////////////////////////////////////////////////////////////////


    }
}
