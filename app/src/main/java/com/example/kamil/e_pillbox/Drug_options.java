package com.example.kamil.e_pillbox;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kamil.e_pillbox.DataAccess.LekarstwoDAO;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;

import java.util.Calendar;

public class Drug_options extends AppCompatActivity {
    EditText nazwa_leku;
    EditText ilosc;
    EditText data_waznosci;
    Button button_dodaj_do_bazy;

    private LekarstwoDAO lekDAO;


    private int day,month,year;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_options);

        final Calendar mcalendar=Calendar.getInstance();


        nazwa_leku=findViewById(R.id.editNazwaLeku);
        ilosc=findViewById(R.id.editIlosc);
        data_waznosci=findViewById(R.id.editDataWaznosci);
        //wprowadzanie daty poprzez kalendarz
        data_waznosci.setOnClickListener(mClickListener);
        day=mcalendar.get(Calendar.DAY_OF_MONTH);
        year=mcalendar.get(Calendar.YEAR);
        month=mcalendar.get(Calendar.MONTH);

//sluzy do ukrycia klawiatury podczas wprowadzania daty
data_waznosci.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(data_waznosci.getWindowToken(),0);
    }
});



        button_dodaj_do_bazy=findViewById(R.id.button_dodaj_do_bazy);
        button_dodaj_do_bazy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLekDoBazy(v);
            }
        });
        lekDAO=new LekarstwoDAO(this);


    }
//wprowadzanie daty poprzez kalendarz
    View.OnClickListener mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog();
        }
    };
    public void DateDialog(){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                data_waznosci.setText(dayOfMonth + "-" + (monthOfYear+1) + "-" + year);
            }};
        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();
    }


    public void addLekDoBazy(View view){
        Lekarstwo lek=new Lekarstwo();
        String nazwaLeku = nazwa_leku.getText().toString();
        String iloscLeku=ilosc.getText().toString();
        String dataLeku = data_waznosci.getText().toString();
        if(!nazwaLeku.isEmpty()) {
            lek.setNazwaLeku(nazwaLeku);
            lek.setIloscOpakowanie(iloscLeku);
            lek.setDataWaznosci(dataLeku);

            lekDAO.insertLek(lek);

            Intent myIntent = new Intent("com.example.kamil.e_pillbox.adddrug");
            sendBroadcast(myIntent);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
         Toast.makeText(this,"Pola nie mogą być puste!\n Wprowadź nazwę",Toast.LENGTH_SHORT).show();
        }
    }


}