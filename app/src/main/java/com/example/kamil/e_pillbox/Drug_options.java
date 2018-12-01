package com.example.kamil.e_pillbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kamil.e_pillbox.DataAccess.LekarstwoDAO;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;

public class Drug_options extends AppCompatActivity {
    EditText nazwa_leku;
    EditText ilosc;
    EditText data_waznosci;
    Button button_dodaj_do_bazy;

    private LekarstwoDAO lekDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_options);




        nazwa_leku=findViewById(R.id.editNazwaLeku);
        ilosc=findViewById(R.id.editIlosc);
        data_waznosci=findViewById(R.id.editDataWaznosci);
        button_dodaj_do_bazy=findViewById(R.id.button_dodaj_do_bazy);
        button_dodaj_do_bazy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLekDoBazy(v);
            }
        });
        lekDAO=new LekarstwoDAO(this);


    }

    public void addLekDoBazy(View view){
        Lekarstwo lek=new Lekarstwo();
        String nazwaLeku = nazwa_leku.getText().toString();
//        int iloscLeku =Integer.parseInt(ilosc.getText().toString());
        String iloscLeku=ilosc.getText().toString();
        String dataLeku = data_waznosci.getText().toString();

        lek.setNazwaLeku(nazwaLeku);
        lek.setIloscOpakowanie(iloscLeku);
        lek.setDataWaznosci(dataLeku);

        lekDAO.insertLek(lek);

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);


    }


}