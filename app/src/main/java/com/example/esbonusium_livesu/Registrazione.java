package com.example.esbonusium_livesu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static com.example.esbonusium_livesu.MainActivity.*;

public class Registrazione extends AppCompatActivity implements View.OnClickListener {
    EditText inputNome, inputPw, inputCittà, inputPw2, data;
    Button btn;
    DatePickerFragment datePickerFragment;
    Intent showResult;
    String username, pw, città, date, pw2;

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



        username = inputNome.getText().toString();
        pw = inputPw.getText().toString();
        città = inputCittà.getText().toString();
        date = data.getText().toString();
        pw2 = inputPw2.getText().toString();

        btn = findViewById(R.id.insButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkInput()){
                    UpdateUtente();
                    showResult = new Intent(Registrazione.this, MainActivity.class);
                    startActivity(showResult);
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

    private void UpdateUtente(){
        int i = users.length - 1;
        int j = 0;
        int index = j;
        //cerco un id libero
        for(int x = 0; x < users.length; x++){
            if(users[x].getNome().equals("")){
                index = x;
            }
        }
        /*if(users[j].getNome() == "") {
            index = j;
        }else {
            j++;
            if (users[j].getNome() == "") {
                index = j;
            }else{
                j++;
                if (users[j].getNome() == "") {
                    index = j;
                }
            }
        }*/

        users[index].setCittà(this.inputCittà.getText().toString());
        users[index].setNome(this.inputNome.getText().toString());
        users[index].setPw(this.inputPw.getText().toString());
        users[index].setBirthDate(this.data.getText().toString());
        users[index].setId(index);

    }

    @Override
    public void onClick(View view) {

    }

    private boolean checkInput(){
        int errors = 0;
        if (inputNome.getText() == null || inputNome.getText().length() == 0) {
            inputNome.setError("Inserire username");
            errors++;
        }else{
            inputNome.setError(null);
        }

        if (inputPw.getText() == null || inputPw.getText().length() == 0) {
            inputPw.setError("Inserire password");
            errors++;
        }else{
            inputPw.setError(null);
        }

        if (inputCittà.getText() == null || inputCittà.getText().length() == 0) {
            inputCittà.setError("Inserire città");
            errors++;
        }else{
            inputCittà.setError(null);
        }

        if (inputPw2.getText() == null || inputPw2.getText().length() == 0) {
            inputPw2.setError("Inserire conferma password");
            errors++;
        }else{
            inputPw2.setError(null);
        }

        if (!inputPw2.getText().toString().equals(inputPw.getText().toString())) {
            inputPw2.setError("Le password non coincidono");
            inputPw.setError("Le password non coincidono");
            inputPw2.setBackgroundResource(R.drawable.edit_text_error);
            inputPw.setBackgroundResource(R.drawable.edit_text_error);

            errors++;
        }

        if(data.getText() == null || data.getText().length() == 0){
            data.setError("Inserire data");
            errors++;
        }else{
            data.setError(null);
        }

        return errors == 0;

    }
}
