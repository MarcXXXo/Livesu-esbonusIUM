package com.example.esbonusium_livesu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Registrazione extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<Utente> users = new ArrayList<Utente>();
    EditText inputNome, inputPw, inputCittà, inputPw2, data;
    Button btn;
    DatePickerFragment datePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        datePickerFragment = new DatePickerFragment();

        inputNome = findViewById(R.id.inputNome);
        inputPw = findViewById(R.id.inputPw);
        data = findViewById(R.id.inputData);
        inputCittà = findViewById(R.id.inputCittà);
        inputPw2 = findViewById(R.id.inputPw2);

        btn = findViewById(R.id.insButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, pw, città, date, pw2;


                username = inputNome.getText().toString();
                pw = inputPw.getText().toString();
                città = inputCittà.getText().toString();
                date = data.getText().toString();
                pw2 = inputPw2.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(Registrazione.this, "Username richiesto", Toast.LENGTH_SHORT).show();
                } else if (pw.equals("")) {
                    Toast.makeText(Registrazione.this, "Password richiesta", Toast.LENGTH_SHORT).show();
                } else if (città.equals("")) {
                    Toast.makeText(Registrazione.this, "Provenienza richiesta", Toast.LENGTH_SHORT).show();
                } else if (pw2.equals("")) {
                    Toast.makeText(Registrazione.this, "Conferma password richiesta", Toast.LENGTH_SHORT).show();
                } else if (!pw2.equals(pw)) {
                    Toast.makeText(Registrazione.this, "Le password non coincidono", Toast.LENGTH_SHORT).show();
                } else {
                    sendUserData(username,pw, città, date);
                    Toast.makeText(Registrazione.this, "Utente Creato", Toast.LENGTH_SHORT).show();
                }
            }

        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //il fragment manager è colui che dirige tutti i fragment
                datePickerFragment.show(getSupportFragmentManager(),"datePicker");
            }
        });

        //questa funzione permette di controllare che l'utente non scriva nella textview
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() { //funzione di view
            @Override
            public void onFocusChange(View v, boolean hasFocus) { //metodo chiamato quando lo stato della view cambia
                if(hasFocus){
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

        //bottoni OK e ANNULLA
        datePickerFragment.setOnDatePickerFragmentChanged(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date) {
                //Associo il comportamento del bottone OK all'edit text della data, voglio che una
                // volta selezionata quindi ho premuto ok, l'edit text mostri la data selezionata
                // tramite il datepicker
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                data.setText(format.format(date.getTime()));
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });
    }
    public void sendUserData(String usern, String pass, String city, String date) {
        users.add(pickInfo(usern,pass,city,date));
        Intent toLog = new Intent(Registrazione.this,MainActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("serialzable",users);
        toLog.putExtras(b);
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        startActivity(toLog);
    }

    public static Utente pickInfo(String usern, String pass, String city, String date) {
        Utente userinfo = new Utente();
        userinfo.setNome(usern);
        userinfo.setPw(pass);
        userinfo.setBirthDate(date);
        userinfo.setCittà(city);
        return userinfo;
    }


    @Override
    public void onClick(View view) {

    }
}
