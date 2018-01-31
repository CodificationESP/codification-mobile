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
import android.widget.GridLayout;
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

public class ReserverChambreAdapter extends BaseAdapter {
    List<Batiment> listebatiments;
    private Context context;
    private AlertDialog dialogue1 = null;
    private AlertDialog dialogue = null;
    SharedPreferences sharedPref ;
    String idetudiant ;


    public ReserverChambreAdapter(List<Batiment> listebatiments, Context context) {
        this.sharedPref = context.getSharedPreferences(
                "user_info", Context.MODE_PRIVATE);
        this.idetudiant = sharedPref.getString("idetudiant", "");
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

            final Chambre ch = (chambres.get(i));
            final Button button = new Button(context);
            button.setText(ch.getNomchambre());
            int nbrow = (chambres.size()/2);
            gridlayoutchambres.setRowCount(nbrow);
            gridlayoutchambres.setColumnCount(4);
            gridlayoutchambres.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Reservation reservation = new Reservation(new Date(),"reserver",idetudiant,ch.getId());
                    afficher_mess("Voulez vous vraiment reserver cette chambre?", reservation);

                }
            });
        }

        return vue;
    }
    protected void  afficher_mess (String mess,final Reservation reservation) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.boitedialogueconfirm, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        TextView message = (TextView) dialogView.findViewById(R.id.message);
        Button buttonconfirm = (Button) dialogView.findViewById(R.id.buttonconfirmer);
        Button buttonannuler = (Button) dialogView.findViewById(R.id.buttonannuler);
        message.setText(mess);
        buttonconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://codification.herokuapp.com/api/";
                Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                final EtudiantInterface client =retrofit.create(EtudiantInterface.class);
                client.getEtudiantAvecReservation(idetudiant).enqueue(new Callback<Etudiant>() {
                    @Override
                    public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                        if (response.isSuccessful()){
                            if (response.body().getReservation()!= null){
                                bdialog("Vous avez déjà réservé");

                            }else {
                                client.reserveretudiant(idetudiant,reservation).enqueue(new Callback<Reservation>() {
                                    @Override
                                    public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                        if (response.isSuccessful()){
                                            Log.d("réservation faite ","!");
                                            dialogue1.dismiss();
                                            bdialog("opération réussie");

                                        }else{
                                            Log.d("réservation non faite ","!");
                                            dialogue1.dismiss();
                                            bdialog("impossible de reserver");
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<Reservation> call, Throwable t) {
                                        Log.d("réservation ","failure");

                                    }
                                });

                            }
                        }
                        else{
                            client.reserveretudiant(idetudiant,reservation).enqueue(new Callback<Reservation>() {
                                @Override
                                public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                    if (response.isSuccessful()){
                                        Log.d("réservation faite ","!");
                                        bdialog("opération réussie");
                                    }else{
                                        Log.d("réservation non faite ","!");
                                        dialogue1.dismiss();
                                    }

                                }

                                @Override
                                public void onFailure(Call<Reservation> call, Throwable t) {
                                    Log.d("réservation ","failure");

                                }
                            });


                        }
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
                dialogue1.dismiss();

            }
        });
        builder.setView(dialogView);
        dialogue1 = builder.create();
        dialogue1.show();
    }
    protected void  bdialog (String mess) {
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

