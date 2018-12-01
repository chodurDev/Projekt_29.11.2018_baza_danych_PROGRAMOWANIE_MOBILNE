package com.example.kamil.e_pillbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.e_pillbox.DataAccess.LekarstwoDAO;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private ListView listaLekow;
private Button bDodajLek;
private ImageButton bWyszukajLek;
private EditText etWyszukaj;
private LekarstwoDAO lekDAO;

    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaLekow=findViewById(R.id.listView);
        bDodajLek=findViewById(R.id.button);
        bWyszukajLek=findViewById(R.id.btWyszukaj);
        etWyszukaj=findViewById(R.id.etWyszukajLek);

        bWyszukajLek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wyszukaj(v);
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


    }


    private void reloadListaLekow() {
        List<Lekarstwo> allLeki = lekDAO.getAllData();
        LekarstwoAdapter adapter = new LekarstwoAdapter(this,allLeki);
        listaLekow.setAdapter(adapter);
        listaLekow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v=view.findViewById(R.id.lekarstwoNazwa);
                Toast.makeText(getApplicationContext(),"zaznaczony Item to: "+v.getText(),Toast.LENGTH_SHORT).show();
                AktywnoscZazylem(view);
            }


        });
        //TODO implementacja długiego kliknięcia
//        listaLekow.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//            }
//        });


    }

    public void AktywnoscDodajLek(View view){
        Intent intent = new Intent(this,Drug_options.class);
        startActivity(intent);

    }
    public void AktywnoscZazylem(View view){
        Intent intent = new Intent(this,Drug.class);
        startActivity(intent);
    }
    public void Wyszukaj(View view){
        String wyszukanaNazwa = etWyszukaj.getText().toString();
        List<Lekarstwo> lek=lekDAO.getLekByNazwa(wyszukanaNazwa);
        LekarstwoAdapter adapter = new LekarstwoAdapter(this,lek);
        listaLekow.setAdapter(adapter);

    }
}
