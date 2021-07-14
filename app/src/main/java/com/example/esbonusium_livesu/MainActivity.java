package com.example.esbonusium_livesu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    EditText inputNome, inputPw;
    Button loginBtn;
    TextView regBtn;
    Utente user;

    public static final String PERSON_EXTRA="package com.eventium.myapplication";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        inputNome = findViewById(R.id.inputNome);
        inputPw = findViewById(R.id.inputPw);

        loginBtn = findViewById(R.id.accediBtn);
        regBtn = findViewById(R.id.Register);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeC, passC, nome, pass;
                nome = inputNome.getText().toString();
                pass = inputPw.getText().toString();
                ArrayList<Utente> users = (ArrayList<Utente>) getIntent().getSerializableExtra("serialzable");

                for(int i = 0; i < users.size(); i++){
                    nomeC = users.get(i).getNome();
                    passC = users.get(i).getPw();
                    if(nome.equals(nomeC) && pass.equals(passC)){
                        Toast.makeText(MainActivity.this, "Utente loggato", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Registrazione.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        user = null;
    }
}