package com.example.kamil.e_pillbox;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.kamil.e_pillbox.DataAccess.LekarstwoDAO;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyServiceDataWaznosci extends Service {
    Toast toast;
    private LekarstwoDAO lekDAO;
    public MyServiceDataWaznosci() {

    }


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();


         lekDAO=new LekarstwoDAO(this);
        List<Lekarstwo> allLeki = lekDAO.getAllData();

        for(Lekarstwo lekarstwo:allLeki){
            try {
                Date data_waznosci=new SimpleDateFormat("dd-MM-yyyy").parse(lekarstwo.getDataWaznosci());

                if(data_waznosci.before(currentDate)) {
                    Log.d("usluga_epilbox", lekarstwo.getNazwaLeku() + " przeterminowany");
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.pill)
                            .setContentTitle("E-pillBox")
                            .setContentText(lekarstwo.getNazwaLeku() + " przeterminowany")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                    notificationManager.notify(lekarstwo.getId(), mBuilder.build());
                    // Toast.makeText(this, lekarstwo.getNazwaLeku() + " przeterminowany", Toast.LENGTH_SHORT).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
