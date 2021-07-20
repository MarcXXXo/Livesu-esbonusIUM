package com.example.esbonusium_livesu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.esbonusium_livesu.MainActivity.*;

import java.util.ArrayList;

public class AdminUsersView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users_view);
        final ListView list = findViewById(R.id.list);
        Button home;
        home = findViewById(R.id.homeBtn2);

        ArrayList<String> arrayList = new ArrayList<>();
        for(int i = 0; i < users.length; i++){
            if(!users[i].getAdmin() && !users[i].getNome().equals("")) {
                arrayList.add(users[i].getNome());
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_list, R.id.nomeUtente, arrayList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener((parent, view, position, id) -> {
            String clickedItem=(String) list.getItemAtPosition(position);
            Toast.makeText(AdminUsersView.this,clickedItem,Toast.LENGTH_LONG).show();
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(AdminUsersView.this, Home.class);
                startActivity(j);
            }
        });

    }

    public void abilitaAdmin(View view){
        TextView n = findViewById(R.id.nomeUtente);
        for(int i = 0; i < users.length; i++){
            if(users[i].getNome().equals(n.getText().toString())) {
                users[i].setAdmin(true);
                Toast.makeText(AdminUsersView.this, ""+n.getText().toString()+" promosso Admin" , Toast.LENGTH_SHORT).show();
                Intent j = new Intent(AdminUsersView.this, Home.class);
                startActivity(j);
            }
        }
    }
}