package com.example.kamil.e_pillbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kamil.e_pillbox.pojo.Lekarstwo;

import org.w3c.dom.Text;


import java.util.List;

public class LekarstwoAdapter extends ArrayAdapter {

    public LekarstwoAdapter(Context context, List<Lekarstwo> lekarstwo) {
        super(context, 0,lekarstwo);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lekarstwo lekarstwo= (Lekarstwo) getItem(position);
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.lekarstwo,parent,false);
        }
        TextView tvNazwa=convertView.findViewById(R.id.lekarstwoNazwa);
        TextView tvIlosc=convertView.findViewById(R.id.lekarstwoIlosc);
        TextView tvData=convertView.findViewById(R.id.lekarstwoData);

        tvNazwa.setText(lekarstwo.getNazwaLeku());
        tvIlosc.setText(lekarstwo.getIloscOpakowanie());
        tvData.setText(lekarstwo.getDataWaznosci());

        return convertView;
    }
}
