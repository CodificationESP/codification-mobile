package com.codification.codificationesp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arame on 29/01/2018.
 */

public class ChangerReservationAdapter extends BaseAdapter{
    Chambre chambre;
    private Context context;
    private AlertDialog dialogue = null;
    ListView l;
    List<Batiment> batiments;
    String url = "https://codification.herokuapp.com/api/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    EtudiantInterface client =retrofit.create(EtudiantInterface.class);
    SharedPreferences sharedPref ;
    String idetudiant ;

    public ChangerReservationAdapter(Chambre chambre, Context context,ListView l) {
        this.sharedPref = context.getSharedPreferences(
                "user_info", Context.MODE_PRIVATE);
        this.idetudiant = sharedPref.getString("idetudiant", "");
        this.chambre = chambre;
        this.context = context;
        this.l = l ;
    }

    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return chambre;
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
            vue = inflater.inflate(R.layout.chambrereservee,null);
        }
        else{
            vue = convertView;
        }
        copvue = vue;
        Button button = (Button) vue.findViewById(R.id.buttonaction);
        final Button buttonchbre = (Button) vue.findViewById(R.id.buttonchbrereservee);
        button.setText("Changer reservation");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BatimentInterface client = retrofit.create(BatimentInterface.class);

                client.getBatiments().enqueue(new Callback<List<Batiment>>() {
                    @Override
                    public void onResponse(Call<List<Batiment>> call, Response<List<Batiment>> response) {
                        if (response.isSuccessful()) {
                            Log.d("MainActivity", "recuperation batiment success");

                            batiments = response.body();
                            ReserverChambreAdapter adapterbatiment = new ReserverChambreAdapter(batiments,context);
                            l.setAdapter(adapterbatiment);
                        }else{
                            Log.d("MainActivity", "recuperation batiment failure");
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Batiment>> call, Throwable t) {
                        Log.d("MainActivity", "recuperation failure");
                    }
                });
//

            }
        });
        client.getEtudiantAvecReservation(idetudiant).enqueue(new Callback<Etudiant>() {
            @Override
            public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {

                buttonchbre.setText(response.body().getReservation().getChambre().getNomchambre());

            }

            @Override
            public void onFailure(Call<Etudiant> call, Throwable t) {

            }
        });


        return vue;
    }
    protected void  codifier (String mess,final Codification codification) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.boitedialogueconfirm, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        TextView message = (TextView) dialogView.findViewById(R.id.message);
        Button buttonconfirm = (Button) dialogView.findViewById(R.id.buttonconfirmer);
        Button buttonannuler = (Button) dialogView.findViewById(R.id.buttonannuler);
        message.setText(mess);
        buttonconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                client.codifieretudiant(idetudiant, codification).enqueue(new Callback<Etudiant>() {
                    @Override
                    public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                        bdialogReussite("Codification réussie");

                    }

                    @Override
                    public void onFailure(Call<Etudiant> call, Throwable t) {

                    }
                });

            }
        });
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
    protected void  bdialogReussite (String mess) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.boitedialogueok, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        TextView message = (TextView) dialogView.findViewById(R.id.textViewmessageok);
        Button buttonok = (Button) dialogView.findViewById(R.id.buttonok);
        message.setText(mess);

        buttonok.setOnClickListener(new View.OnClickListener() {
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
