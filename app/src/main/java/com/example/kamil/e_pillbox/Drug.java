package com.example.kamil.e_pillbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
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
private EditText etDawka;

public SharedPreferences sharedPreferences;

private LekarstwoDAO lekDAO;

    @SuppressLint("WorldReadableFiles")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);


        lekDAO=new LekarstwoDAO(this);
        final Lekarstwo lek=new Lekarstwo();

        nazwaLeku=findViewById(R.id.drug_name);
        Intent i=getIntent();
        final Lekarstwo lekarstwo=(Lekarstwo)i.getSerializableExtra("lek_obiekt");


        nazwaLeku.setText(lekarstwo.getNazwaLeku()+" "+lekarstwo.getIloscOpakowanie());

        btZazylem=findViewById(R.id.btZazylem);
        etDawka=findViewById(R.id.etDawka);
        onLoad();

        btUsun = findViewById(R.id.btUsun);
        btUsun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usunZBazy(v,lekarstwo);
            }
        });
    }
    public void usunZBazy(View view,Lekarstwo lekarstwo){
        lekDAO.deleteLek(lekarstwo);
        Toast.makeText(this,"usunieto z bazy ",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void onLoad(){

        String string = sharedPreferences.getString("dawkaDomyslna", "nie dziala");
        etDawka.setText(string);
    }
}
