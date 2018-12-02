package com.example.kamil.e_pillbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.e_pillbox.DataAccess.LekarstwoDAO;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;

public class Drug extends AppCompatActivity {
private TextView nazwaLeku;
private Button btUsun;

private LekarstwoDAO lekDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        lekDAO=new LekarstwoDAO(this);
        final Lekarstwo lek=new Lekarstwo();

        nazwaLeku=findViewById(R.id.drug_name);



        nazwaLeku.setText(getIntent().getStringExtra("lek_nazwa")+" "+getIntent().getStringExtra("lek_ilosc"));

        btUsun = findViewById(R.id.btUsun);
        btUsun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usunZBazy(v,getIntent().getStringExtra("lek_nazwa"));
            }
        });
    }
    public void usunZBazy(View view,String lekNazwa){
        lekDAO.deleteLek(lekNazwa);
        Toast.makeText(this,"usunieto z bazy ",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
        //TODO wywaołanie metody do usuwania elementu z bazy
    }
}
