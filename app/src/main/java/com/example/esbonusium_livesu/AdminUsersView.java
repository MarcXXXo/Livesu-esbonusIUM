package com.example.esbonusium_livesu;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.esbonusium_livesu.MainActivity.*;

import java.util.ArrayList;

public class AdminUsersView extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {
    SearchView barra;
    public ArrayList<String> arrayList1 = new ArrayList<>();
    public ArrayList<String> utentifiltrati = new ArrayList<>();

    Button home;
    ImageView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users_view);
        barra = findViewById(R.id.searchView);
        search = barra.findViewById(androidx.appcompat.R.id.search_mag_icon);
        barra.setOnQueryTextListener(this);
        home = findViewById(R.id.homeBtn2);
        barra.setQueryHint("Cerca evento..");
        ListView list = findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_list, R.id.nomeUtente, arrayList1);

        for (int i = 0; i < users.length; i++) {
            if (!users[i].getAdmin() && !users[i].getNome().equals("")) {
                arrayList1.add(users[i].getNome());
            }
        }

        utentifiltrati = arrayList1;
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_list, R.id.nomeUtente, arrayList1);
        list.setAdapter(arrayAdapter);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(AdminUsersView.this, Home.class);
                startActivity(j);
            }
        });
    }

    public void abilitaAdmin(View view) {
        ListView list = findViewById(R.id.list);
        LinearLayout vwParentRow = (LinearLayout)view.getParent();
        TextView child = (TextView)vwParentRow.findViewById(R.id.nomeUtente);
        Button btnChild = (Button)vwParentRow.findViewById(R.id.button);
        String key = (String)child.getText();

        for (int i = 0; i < users.length; i++) {
            if (users[i].getNome().equals(key)) {
                users[i].setAdmin(true);
                Toast.makeText(AdminUsersView.this, "" + key + " promosso Admin", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(AdminUsersView.this, Home.class);
                startActivity(j);
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ListView list = findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_list, R.id.nomeUtente, utentifiltrati);
        arrayAdapter.clear();
        arrayAdapter.notifyDataSetChanged();
        if(barra.getQuery().length() == 0) {
            arrayAdapter.clear();
            arrayAdapter.notifyDataSetChanged();
            reset();
        }


        if (barra.getQuery() == null || barra.getQuery().toString().trim().equals("")) {
            return false;
        }

        for (int i = 0; i < users.length; i++) { //ricerca per titolo evento
            if (!users[i].getAdmin() && !users[i].getNome().equals("") && users[i].getNome().toLowerCase().contains(newText.toLowerCase())) {
                utentifiltrati.add(users[i].getNome());
            }
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_list, R.id.nomeUtente, utentifiltrati);
        list.setAdapter(arrayAdapter);
        return false;

    }

    public void reset(){
        ArrayList<String> supp = new ArrayList<>();
        ListView list = findViewById(R.id.list);
        if(barra.getQuery().length() == 0) {
            for (int i = 0; i < users.length; i++) {
                if (!users[i].getAdmin() && !users[i].getNome().equals("")) {
                    supp.add(users[i].getNome());
                }
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_list, R.id.nomeUtente, supp);
            list.setAdapter(arrayAdapter);
        }
    }
}