package com.example.kamil.e_pillbox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import android.support.v4.app.FragmentActivity;



public class AddDrugReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.d("adddrug", "Dodano lek do bazy");

        CharSequence text = "Dodano lek do bazy!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();

    }




}

