package com.example.kamil.e_pillbox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AddDrugReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.d("adddrug", "Dodano lek do bazy");

        CharSequence text = "Dodano lek do bazy!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }
}

