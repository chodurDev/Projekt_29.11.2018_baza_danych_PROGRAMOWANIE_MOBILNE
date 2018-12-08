package com.example.kamil.e_pillbox;

import android.content.Context;
import android.telephony.VisualVoicemailService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kamil.e_pillbox.DataAccess.LekarstwoDAO;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;

import org.w3c.dom.Text;


import java.util.List;

import static java.lang.Boolean.valueOf;


public class LekarstwoAdapter extends ArrayAdapter {
    public static String TAG=" epilbox";
    LekarstwoDAO lekDAO=new LekarstwoDAO(getContext());

    public LekarstwoAdapter(Context context, List<Lekarstwo> lekarstwo) {
        super(context, 0,lekarstwo);
    }







    

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Lekarstwo lekarstwo= (Lekarstwo) getItem(position);
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.lekarstwo,parent,false);
        }
        TextView tvNazwa=convertView.findViewById(R.id.lekarstwoNazwa);
        TextView tvIlosc=convertView.findViewById(R.id.lekarstwoIlosc);
        TextView tvData=convertView.findViewById(R.id.lekarstwoData);
        final CheckBox cbZazyte=convertView.findViewById(R.id.cbZazyte);

        tvNazwa.setText(lekarstwo.getNazwaLeku());
        tvIlosc.setText(lekarstwo.getIloscOpakowanie());
        tvData.setText(lekarstwo.getDataWaznosci());

        cbZazyte.setChecked(valueOf(lekarstwo.getIsZazyte()));

        cbZazyte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean sprawdzenie=cbZazyte.isChecked();
                //Toast.makeText(getContext(),"działa "+sprawdzenie,Toast.LENGTH_SHORT).show();
                lekarstwo.setIsZazyte(String.valueOf(sprawdzenie));

                lekDAO.updateLekByCheckBox(lekarstwo);

            }
        });

        return convertView;
    }
}
