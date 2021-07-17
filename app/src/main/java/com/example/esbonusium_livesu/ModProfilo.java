package com.example.esbonusium_livesu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModProfilo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_profilo);

        Button mod, home;
        EditText pw1, pw2;

        mod = findViewById(R.id.confermaBtn);
        home = findViewById(R.id.homeBtn);
        pw1 = findViewById(R.id.pwlog);
        pw2 = findViewById(R.id.pwlog2);



        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pw1.getText().toString().equals(pw2.getText().toString())) {

                    Toast.makeText(ModProfilo.this, "Password Cambiata", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ModProfilo.this, Home.class);
                    startActivity(i);
                } else {
                    pw1.setError("Le password non coincidono");
                    pw2.setError("Le password non coincidono");
                    pw1.setBackgroundResource(R.drawable.edit_text_error);
                    pw2.setBackgroundResource(R.drawable.edit_text_error);
                }

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(ModProfilo.this, Home.class);
                startActivity(j);

            }
        });
    }
}