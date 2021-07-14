package com.example.esbonusium_livesu;

import java.io.Serializable;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import androidx.fragment.app.DialogFragment;


public class Utente implements Serializable {

    private String nome, password, città, data;
    private int id;

    public Utente(String nome, String password, String data, String città, int id) {
        this.setNome(nome);
        this.setBirthDate(data);
        this.setPw(password);
        this.setCittà(città);
        this.setId(id);
    }

    public Utente() {
        this.setNome("");
        this.setBirthDate(null);
        this.setPw("");
        this.setCittà("");
        this.setId(0);
    }
    public Utente(int id) {
        this.setNome("");
        this.setBirthDate(null);
        this.setPw("");
        this.setCittà("");
        this.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getPw() {
        return password;
    }

    public void setPw(String password) {
        this.password = password;
    }

    public void setBirthDate(String birthDate) {
        this.data = birthDate;
    }

    public String getBirthDate() { return data; }

    public void setId(int id){ this.id = id;}

    public int getId(){ return id;}
}
