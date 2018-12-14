package com.example.bobrik.santiagodiazmuseo.View;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bobrik.santiagodiazmuseo.Model.Pintura;
import com.example.bobrik.santiagodiazmuseo.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterPintura extends RecyclerView.Adapter<AdapterPintura.PinturaHolder> {

    private List<Pintura> pinturaList;
    private Comunicacion comunicacion;


    public AdapterPintura(List<Pintura> pinturaList, Comunicacion comunicacion) {
        this.pinturaList = pinturaList;
        this.comunicacion = comunicacion;
    }

    public void setPinturaList(List<Pintura> pinturaList){
        this.pinturaList = pinturaList;


        notifyDataSetChanged();


    }

    @NonNull
    @Override
    public PinturaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pintura, viewGroup, false);
        PinturaHolder pinturaHolder = new PinturaHolder(view);
        return pinturaHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PinturaHolder pinturaHolder, int i) {

        Pintura pintura = pinturaList.get(i);
        pinturaHolder.cargar(pintura);

    }

    public interface Comunicacion{
        void irDetalle(Pintura pintura);
    }

    @Override
    public int getItemCount() {
        return pinturaList.size();
    }

    public class PinturaHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
         private ImageView imageView;
        private FirebaseStorage firebaseStorage;


        public PinturaHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagenRecycler);
            textViewTitle = itemView.findViewById(R.id.textViewName);
            firebaseStorage = FirebaseStorage.getInstance();


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pintura pintura = pinturaList.get(getAdapterPosition());
                    comunicacion.irDetalle(pintura);
                }
            });


        }



        public void cargar(Pintura pintura) {

            textViewTitle.setText(pintura.getName());
            StorageReference imagenes = firebaseStorage.getReference();


            firebaseStorage = FirebaseStorage.getInstance();
            StorageReference raizStorge = firebaseStorage.getReference();
            imagenes = raizStorge.child(pintura.getImage());
            imagenes.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(imageView.getContext()).load(uri).into(imageView);
                }
            });

        }
    }



}



