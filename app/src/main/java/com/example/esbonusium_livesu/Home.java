package com.example.esbonusium_livesu;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.esbonusium_livesu.MainActivity.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    TextView user, pass, data, città, nome, mod;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = findViewById(R.id.userlog);
        pass = findViewById(R.id.pwlog);
        data = findViewById(R.id.birthlog);
        città = findViewById(R.id.citylog);
        nome = findViewById(R.id.nomeLog);
        logout = findViewById(R.id.logoutBtn);
        mod = findViewById(R.id.modProfilo);


        nome.setText(users[logged].getNome());

        user.setText(users[logged].getNome());
        pass.setText(users[logged].getPw());
        data.setText(users[logged].getBirthDate());
        città.setText(users[logged].getCittà());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Logout Effettuato", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);

            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }
}