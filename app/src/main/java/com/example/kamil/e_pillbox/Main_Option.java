package com.example.kamil.e_pillbox;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main_Option extends AppCompatActivity {
    EditText texttosave;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__option);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        init();
    }

    private void init(){

        texttosave =  findViewById(R.id.editText);

        onLoad();

    }

    public void onSave(View view) {
        String sText = texttosave.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dawkaDomyslna", sText);

        editor.commit();
        Toast.makeText(this,"Zapisano w pamięci",Toast.LENGTH_SHORT).show();


    }

    public void onLoad(){

        String string = sharedPreferences.getString("dawkaDomyslna", "");
        texttosave.setText(string);
    }
}
