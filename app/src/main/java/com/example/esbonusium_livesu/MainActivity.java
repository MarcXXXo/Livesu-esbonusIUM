package com.example.esbonusium_livesu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText inputNome, inputPw;
    Button loginBtn;
    TextView regBtn;
    Utente user;

    public static final String PERSON_EXTRA = "package com.eventium.myapplication";
    public static Utente[] users = {new Utente("admin", "admin", "01/01/2000", "", 0), new Utente(1), new Utente(2), new Utente(3)}; //tutti gli users registrati
    public static int logged; //variabile globale id dell'utente loggato


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
                boolean userFound = false;
                for (int i = 0; i <= users.length - 1; i++) { //controllo le credenziali degli users salvati
                    if ((inputNome.getText().toString().equals(users[i].getNome()) && inputPw.getText().toString().equals(users[i].getPw()))) { //se le trovo login
                        userFound = true;
                        logged = users[i].getId();
                        //Intent hom = new Intent(MainActivity.this, Home.class);
                        //startActivity(hom);
                        Log.d("Prova", "" + i + users[i].getNome());
                        //credenziali corrette indirizzare alla Home
                        Intent j = new Intent(MainActivity.this, Home.class);
                        startActivity(j);
                    }
                }
                if (userFound == false) { //altrimenti popup
                    inputPw.setBackgroundResource(R.drawable.edit_text_error);
                    inputNome.setBackgroundResource(R.drawable.edit_text_error);
                    inputNome.setError("Credenziali Errate");

                    //credenziali sbagliate avvisi in rosso
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
    private boolean checkI(){
        int errors = 0;
        if (inputNome.getText().toString().equals("")) {
            inputNome.setError("Inserire username");
            errors++;
        }
        if (inputPw.getText().toString().equals("")) {
            inputPw.setError("Inserire password");
            errors++;
        }

        if(errors == 0){
            inputNome.setError(null);
            inputPw.setError(null);
            return true;
        }else{
            return false;
        }
    }
}

