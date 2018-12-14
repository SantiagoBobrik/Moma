package com.example.bobrik.santiagodiazmuseo.View;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bobrik.santiagodiazmuseo.R;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PanelUser extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_user);
        TextView textViewNombrre = findViewById(R.id.nombre);
        ImageView imageViewFoto = findViewById(R.id.foto);
        Button button =findViewById(R.id.cerrarSesion);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        textViewNombrre.setText(currentUser.getDisplayName());

        Profile profile = Profile.getCurrentProfile();

        Uri uri = currentUser.getPhotoUrl();
        Glide.with(this).load(uri).into(imageViewFoto);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(PanelUser.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    }

