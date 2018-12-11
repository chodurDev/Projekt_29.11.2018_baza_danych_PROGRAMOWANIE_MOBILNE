package com.example.kamil.e_pillbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main_Option extends AppCompatActivity {
    private EditText etDawkaOptions;
    private Button btDawkaOptions;
    static Main_Option INSTANCE;
    public SharedPreferences sharedPreferences;

    String data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__option);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        etDawkaOptions = findViewById(R.id.etDawkaOptions);
        btDawkaOptions = findViewById(R.id.btDawkaOptions);

        onLoad();
        btDawkaOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onSave();
                returnToActivity(v);
            }
        });
        INSTANCE = this;


    }

    public static Main_Option getActivityInstance() {
        return INSTANCE;
    }

    public String getData() {
        data = sharedPreferences.getString("dawkaDomyslna", "");
        return this.data;
    }

    public void returnToActivity(View view) {

    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    }
    public void onSave() {
        String sText = etDawkaOptions.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dawkaDomyslna", sText);

        editor.commit();
//        Toast.makeText(this,"Zapisano w pamięci",Toast.LENGTH_SHORT).show();


    }

    public void onLoad(){
        data = sharedPreferences.getString("dawkaDomyslna", "");
        etDawkaOptions.setText(data);
    }
}
