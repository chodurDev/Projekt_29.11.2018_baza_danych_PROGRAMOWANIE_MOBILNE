package com.example.kamil.e_pillbox;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main_Option extends AppCompatActivity {
    EditText texttosave;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__option);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        init();
    }

    private void init(){

        texttosave =  findViewById(R.id.editText);

        onLoad();

    }

    public void onSave(View view) {
        String sText = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            sText = texttosave.getText().toString();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("texttosave", sText);

        editor.commit();
        Toast.makeText(this,"Zapisano w pamięci",Toast.LENGTH_SHORT).show();


    }

    public void onLoad(){

        String string = sharedPreferences.getString("texttosave", "");
        texttosave.setText(string);
    }
}
