package com.example.kamil.e_pillbox;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.e_pillbox.DataAccess.LekarstwoDAO;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity {
private ListView listaLekow;
private Button bDodajLek;
private EditText etWyszukaj;

private ImageButton bOption;

private LekarstwoDAO lekDAO;

//TODO Zrobic obsługe jesli jest klikniety checkbox przeładować obraz

    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, MyServiceDataWaznosci.class);
        startService(serviceIntent);


        listaLekow=findViewById(R.id.listView);
        bDodajLek=findViewById(R.id.button);
        etWyszukaj=findViewById(R.id.etWyszukajLek);
        bOption=findViewById(R.id.imageButton);

        bOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AktywnoscOpcjeGlowne(v);
            }
        });


        bDodajLek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AktywnoscDodajLek(v);
            }
        });
        lekDAO=new LekarstwoDAO(this);

        reloadListaLekow();

//dynamiczne wyszukiwanie po nazwie
            etWyszukaj.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                   }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String wyszukajLekInput =etWyszukaj.getText().toString();
                    if(wyszukajLekInput.isEmpty()){
                        reloadListaLekow();
                    }else{
                        reloadBySearching(wyszukajLekInput);
                    }

                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        listaLekow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getSelectedItem();
                AktywnoscZazylem(view,(Lekarstwo)parent.getItemAtPosition(position));

            }
        });



    }






//ladowanie calej listy lekow
    public void reloadListaLekow() {
        List<Lekarstwo> allLeki = lekDAO.getAllData();
        LekarstwoAdapter adapter = new LekarstwoAdapter(this,allLeki);
        listaLekow.setAdapter(adapter);


    }
//dynamiczne wyszukiwanie po nazwie
    public void reloadBySearching(String wyszukajLekInput){

        List<Lekarstwo> lek = lekDAO.getLekByNazwa(wyszukajLekInput);
        LekarstwoAdapter adapter = new LekarstwoAdapter(this, lek);
        listaLekow.setAdapter(adapter);
    }
    public void AktywnoscDodajLek(View view){
        Intent intent = new Intent(this,Drug_options.class);
        startActivity(intent);

    }
    public void AktywnoscZazylem(View view,Lekarstwo lekarstwo){
        Intent intent = new Intent(this,Drug.class);
        intent.putExtra("lek_obiekt", lekarstwo);

        startActivity(intent);
    }


    public void AktywnoscOpcjeGlowne(View view){
        Intent intent = new Intent(this,Main_Option.class);
        startActivity(intent);
    }


}
