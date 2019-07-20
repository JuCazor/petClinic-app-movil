package com.example.petclinicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PetClinic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_clinic2);
        setTheme(R.style.noActionBar);

        Button btnHacerCita = findViewById(R.id.btnHacerCita);

        btnHacerCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaPantalla = new Intent(PetClinic.this, HacerCita.class);
                startActivity(nuevaPantalla);
            }
        });

    }
}
