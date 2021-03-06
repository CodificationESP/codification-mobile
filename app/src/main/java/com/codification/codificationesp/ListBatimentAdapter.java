package com.codification.codificationesp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arame on 20/01/2018.
 */

public class ListBatimentAdapter extends BaseAdapter {
    List<Batiment> listebatiments;
    private Context context;
    private AlertDialog dialogue = null;

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
        final List<Chambre> chambres = batiment.getChambresbatiment();
        GridLayout gridlayoutchambres = (GridLayout) vue.findViewById(R.id.gridlayoutchambre);
        for(int i=0; i<chambres.size(); i++){

            Chambre ch = (chambres.get(i));
            Button button = new Button(context);
            button.setText(ch.getNomchambre());
            int nbrow = (chambres.size()/2);
            gridlayoutchambres.setRowCount(nbrow);
            gridlayoutchambres.setColumnCount(4);
            gridlayoutchambres.addView(button);
        }

        return vue;
    }
    protected void  afficher_mess (String mess) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.boitedialogueconfirm, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        TextView message = (TextView) dialogView.findViewById(R.id.message);
        Button buttonconfirm = (Button) dialogView.findViewById(R.id.buttonconfirmer);
        Button buttonannuler = (Button) dialogView.findViewById(R.id.buttonannuler);
        message.setText(mess);
        buttonannuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogue.dismiss();

            }
        });
        builder.setView(dialogView);
        dialogue = builder.create();
        dialogue.show();
    }
}
