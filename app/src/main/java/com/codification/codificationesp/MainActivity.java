package com.codification.codificationesp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String url = "https://codification.herokuapp.com/api/";
    private AlertDialog dialogue = null;
    List<Batiment> batiments;
    SharedPreferences sharedPref;
    String idetudiant;
    Context context = this;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPref = getSharedPreferences(
                "user_info", Context.MODE_PRIVATE);
        idetudiant = sharedPref.getString("idetudiant", "");
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ListView l = (ListView) findViewById(R.id.l);
        AccueilAdapter adapterbatiment = new AccueilAdapter(context);
        l.setAdapter(adapterbatiment);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_codifier) {
            final EtudiantInterface client =retrofit.create(EtudiantInterface.class);
            client.getCodificationEtudiant(idetudiant).enqueue(new Callback<Codification>() {
                @Override
                public void onResponse(Call<Codification> call, Response<Codification> response) {
                    if (response.isSuccessful()){
                        bdialog("Vous avez déjà codifié");
                    }else {
                        client.getEtudiantAvecReservation(idetudiant).enqueue(new Callback<Etudiant>() {
                            @Override
                            public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                                if (response.isSuccessful()){
                                    final Reservation reservation = response.body().getReservation();
                                        if (response.isSuccessful()){
                                                if (reservation != null) {
                                                    Chambre chambre = new Chambre(reservation.getChambreId(), reservation.getChambre().getNomchambre(), reservation.getChambre().getCouloirId());
                                                    CodiferChambreAdapter adapterchambre = new CodiferChambreAdapter(chambre, context);
                                                    ListView l = (ListView) findViewById(R.id.l);
                                                    l.setAdapter(adapterchambre);
                                                    Log.d("getreservation ", "failure");
                                                }
                                                else {
                                                    bdialog("Reservée une chambre d'abord");
                                                }
                                        }

                                }else{
                                    bdialog("Reservée une chambre d'abord");
                                }
                            }

                            @Override
                            public void onFailure(Call<Etudiant> call, Throwable t) {

                                    Log.d("getreservation ","failure");
                            }
                        });
                    }

                }

                @Override
                public void onFailure(Call<Codification> call, Throwable t) {


                }
            });

        } else if (id == R.id.nav_reserver) {

            BatimentInterface client = retrofit.create(BatimentInterface.class);

            client.getBatiments().enqueue(new Callback<List<Batiment>>() {
                @Override
                public void onResponse(Call<List<Batiment>> call, Response<List<Batiment>> response) {
                    if (response.isSuccessful()) {
                        Log.d("MainActivity", "recuperation batiment success");

                        batiments = response.body();
                        Log.d("Nombatiment", batiments.get(0).getNombatiment());
                        List<Etage> etages = batiments.get(0).getEtages();
                        Log.d("Numetage",String.valueOf(etages.get(1).getNumEtage()));
                        List<Chambre> chambres = etages.get(1).getListechambres();
                        Log.d("Numchambre",chambres.get(0).getNomchambre());
                        Log.d("Numchambre",chambres.get(1).getNomchambre());
                        Log.d("Numchambre",chambres.get(2).getNomchambre());


                        ListView l = (ListView) findViewById(R.id.l);
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
//            ListView l = (ListView) findViewById(R.id.l);
//            ReserverChambreAdapter adapterbatiment = new ReserverChambreAdapter(batiments,this);
//            l.setAdapter(adapterbatiment);


        } else if (id == R.id.nav_deconnecter) {
            sharedPref.edit().remove("iduser");
            sharedPref.edit().remove("idetudiant");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_accueil) {
            ListView l = (ListView) findViewById(R.id.l);
            AccueilAdapter adapterbatiment = new AccueilAdapter(context);
            l.setAdapter(adapterbatiment);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void  bdialog (String mess) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.boitedialogueok, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
