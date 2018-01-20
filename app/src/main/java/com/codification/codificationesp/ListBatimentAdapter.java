package com.codification.codificationesp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Arame on 20/01/2018.
 */

public class ListBatimentAdapter extends BaseAdapter {
    List<Batiment> listebatiments;
    private Context context;

    public ListBatimentAdapter(List<Batiment> listebatiments, Context context) {
        this.listebatiments = listebatiments;
        this.context = context;
    }

    public int getCount() {
        return listebatiments.size();
    }

    @Override
    public Object getItem(int position) {
        return listebatiments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListView list;
        View vue;
        final View copvue;
        if (convertView == null) {
            vue = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vue = inflater.inflate(R.layout.batiment,null);
        }
        else{
            vue = convertView;
        }
        copvue = vue;

        final Batiment batiment = (Batiment) getItem(position);
        TextView nom = (TextView) vue.findViewById(R.id.textViewnombatiment);
        nom.setText(" "+batiment.getNombatiment());
        final List<Chambre> chambres = batiment.getListechambres();
        GridLayout gridlayoutchambres = (GridLayout) vue.findViewById(R.id.gridlayoutchambre);
        for(int i=0; i<chambres.size(); i++){

            Chambre ch = (chambres.get(i));
            Button button = new Button(context);
            button.setText(ch.getNumchambre());
            int nbrow = (chambres.size()/2);
            gridlayoutchambres.setRowCount(nbrow);
            gridlayoutchambres.setColumnCount(4);
            gridlayoutchambres.addView(button);
        }

        return vue;
    }
}
