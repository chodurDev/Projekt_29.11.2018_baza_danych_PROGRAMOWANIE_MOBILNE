package com.example.kamil.e_pillbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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


            etWyszukaj.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String wykuszajLekInput =etWyszukaj.getText().toString().trim();
                    if(wykuszajLekInput.isEmpty())reloadListaLekow();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


    }




    private void reloadListaLekow() {
        List<Lekarstwo> allLeki = lekDAO.getAllData();
        LekarstwoAdapter adapter = new LekarstwoAdapter(this,allLeki);



        listaLekow.setAdapter(adapter);

        listaLekow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_nazwa=view.findViewById(R.id.lekarstwoNazwa);
                TextView tv_ilosc=view.findViewById(R.id.lekarstwoIlosc);
                parent.getSelectedItem();
                //AktywnoscZazylem(view,tv_nazwa,tv_ilosc);
                AktywnoscZazylem(view,(Lekarstwo)parent.getItemAtPosition(position));

            }
        });

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
    public void Wyszukaj(View view){
        String wyszukanaNazwa = etWyszukaj.getText().toString();
        if(!wyszukanaNazwa.isEmpty()) {
            List<Lekarstwo> lek = lekDAO.getLekByNazwa(wyszukanaNazwa);
            LekarstwoAdapter adapter = new LekarstwoAdapter(this, lek);
            listaLekow.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(this,"Pole wyszukaj jest puste!\n \t\tWprowadz frazę",Toast.LENGTH_SHORT).show();
        }
    }
}
