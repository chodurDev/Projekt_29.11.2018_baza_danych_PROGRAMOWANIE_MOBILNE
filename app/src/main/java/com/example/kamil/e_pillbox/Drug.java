package com.example.kamil.e_pillbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.e_pillbox.DataAccess.LekarstwoDAO;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;

public class Drug extends AppCompatActivity {
private TextView nazwaLeku;
private Button btUsun;
private Button btZazylem;
public EditText etDawka;

public SharedPreferences sharedPreferences;

private LekarstwoDAO lekDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);


        Main_Option obj=new Main_Option();

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);


        lekDAO=new LekarstwoDAO(this);
        final Lekarstwo lek=new Lekarstwo();

        nazwaLeku=findViewById(R.id.drug_name);
        Intent i=getIntent();
        final Lekarstwo lekarstwo=(Lekarstwo)i.getSerializableExtra("lek_obiekt");


        nazwaLeku.setText(lekarstwo.getNazwaLeku()+" "+(!lekarstwo.getIloscOpakowanie().equals("")?lekarstwo.getIloscOpakowanie():"brak ilosc"));
        btZazylem=findViewById(R.id.btZazylem);
        btZazylem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zazylemLek(v,lekarstwo);
            }
        });

        etDawka=findViewById(R.id.etDawka);

        etDawka.setText(obj.getActivityInstance().getData());

        btUsun = findViewById(R.id.btUsun);
        btUsun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usunZBazy(v,lekarstwo);
            }
        });


    }
    ////////////////////metody-funkcje
    public void usunZBazy(View view,Lekarstwo lekarstwo){
        lekDAO.deleteLek(lekarstwo);
        Toast.makeText(this,"usunieto z bazy ",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);

    }




    public void zazylemLek(View view,Lekarstwo lek){


        final int iloscOpakowanie= !lek.getIloscOpakowanie().equals("")? Integer.parseInt(lek.getIloscOpakowanie()):0;
        final int dawka=Integer.parseInt(etDawka.getText().toString());
        if(iloscOpakowanie > 0) {

            if (iloscOpakowanie>=dawka){

            lek.setIloscOpakowanie(Integer.toString(iloscOpakowanie - dawka));
            lekDAO.updateLekByZazycie(lek);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            }else {
                Toast.makeText(this,"Nie ma \nwystarczajacej ilosci lekarstwa \ndo zazycia podaj mniejszą dawkę",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"Brak tabletek do zazycia!",Toast.LENGTH_SHORT).show();
        }
    }

//    public String getDawkaOptions(Main_Option obj) {
//        if(obj != null) {
//            return obj.getActivityInstance().getData();
//        } else {
//            return "brak domyslnej ilosc";
//        }
//    }
}
