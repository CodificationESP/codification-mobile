package com.codification.codificationesp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InscriptionActivity extends AppCompatActivity {

    String url = "https://codification.herokuapp.com/api/";
    Etudiant etudiant = new Etudiant();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Spinner dropdowndep = (Spinner)findViewById(R.id.spinnerdepartement);
        Spinner dropdownform = (Spinner)findViewById(R.id.spinnerformation);
        Spinner dropdownniv = (Spinner)findViewById(R.id.spinnerniveau);

        EditText numetudiant = (EditText) findViewById(R.id.editTextnumetudiant);
        EditText login = (EditText) findViewById(R.id.editTextusername);
        EditText nometudiant = (EditText) findViewById(R.id.editTextnom);
        EditText prenometudiant = (EditText) findViewById(R.id.editTextprenom);
        EditText emailetudiant = (EditText) findViewById(R.id.editTextemail);
        EditText ddnetudiant = (EditText) findViewById(R.id.editTextddn);
        EditText mdp = (EditText) findViewById(R.id.editTextpassword);
        EditText confirmdp = (EditText) findViewById(R.id.editTextcpwd);

        etudiant.setNumetudiant(numetudiant.getText().toString());
        etudiant.setLogin(login.getText().toString());
        etudiant.setNometudiant(nometudiant.getText().toString());
        etudiant.setPrenometudiant(prenometudiant.getText().toString());
        etudiant.setEmail(emailetudiant.getText().toString());
        etudiant.setDdn(ddnetudiant.getText().toString());
        etudiant.setMotdepasse(mdp.getText().toString());

        String[] itemsdep = new String[]{"Genie informatique","Genie civil", "Genie mecanique","Genie electrique","Gestion"};
        final ArrayAdapter<String> adapterdep = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsdep);
        dropdowndep.setAdapter(adapterdep);

        String[] itemsform = new String[]{"DIC ","DUT"};
        final ArrayAdapter<String> adapterform = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsform);
        dropdownform.setAdapter(adapterform);

        String[] itemsniv = new String[]{"1","2", "3", "4","5"};
        final ArrayAdapter<String> adapterniv = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsniv);
        dropdownniv.setAdapter(adapterniv);

        dropdowndep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                String departement = (String) parent.getItemAtPosition(position);
                etudiant.setDepartement(departement);


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        dropdownform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                String formation = (String) parent.getItemAtPosition(position);
                etudiant.setFormation(formation);


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        dropdownniv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                String niveau = (String) parent.getItemAtPosition(position);
                etudiant.setNiveau(niveau);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        Button bconfirmer = (Button) findViewById(R.id.buttonconfirm);
        bconfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                EtudiantInterface client = retrofit.create(EtudiantInterface.class);
                client.creeretudiant(etudiant).enqueue(new Callback<Etudiant>() {
                    @Override
                    public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                        if (response.isSuccessful()) {
                            Log.d("InscriptionActivity", "inscription success");
                            Etudiant etudiant = response.body();
                            Intent intent = new Intent(InscriptionActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                            //SharedPreferences(user);
                        }else{
                            Log.d("InscriptionActivity", "inscription failure");
                        }
                    }
                    @Override
                    public void onFailure(Call<Etudiant> call, Throwable t) {
                        Log.d("LoginActivity", "connection failure");
                    }
                });
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonFeminin:
                if (checked){
                    etudiant.setGenre("M");

                }

                break;
            case R.id.radioButtonMasculin:
                if (checked){
                    etudiant.setGenre("F");
                    break;

                }
        }
    }
}
