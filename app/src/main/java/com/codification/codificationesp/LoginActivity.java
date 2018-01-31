package com.codification.codificationesp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

public class LoginActivity extends AppCompatActivity {
    String url = "https://codification.herokuapp.com/api/";
    private Typeface existence_light;
    private AlertDialog dialogue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        existence_light = android.graphics.Typeface.createFromAsset(getAssets(), "fonts/existence_light.otf");
        setStyle();
        final Context context = this;

        Button bsignup = (Button) findViewById(R.id.buttonsignup);
        Button bconnect = (Button) findViewById(R.id.buttonLogin);
        bconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
               // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
              //  startActivity(intent);
                EditText editTextLogin = (EditText) findViewById(R.id.editTextLogin);
                EditText editTextmdpasse = (EditText) findViewById(R.id.editTextmdpasse);
                final String login = editTextLogin.getText().toString();
                final String mdp = editTextmdpasse.getText().toString();

                Credentials creds = new Credentials(login, mdp);
                final LoginInterface client = retrofit.create(LoginInterface.class);
                client.login(creds).enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if (response.isSuccessful()) {
                            Log.d("LoginActivity", "connection success");

                            SharedPreferences sharedPref = context.getSharedPreferences(
                                    "user_info", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("iduser",response.body().userId);
                            editor.apply();

                            EtudiantInterface  etudiant = retrofit.create(EtudiantInterface.class);
                            etudiant.getetudiant(response.body().userId).enqueue(new Callback<Etudiant>() {
                                @Override
                                public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                                    if (response.isSuccessful()){
                                        SharedPreferences sharedPref = context.getSharedPreferences(
                                                "user_info", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putString("idetudiant",response.body().getNumetudiant());
                                        editor.apply();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }

                                }

                                @Override
                                public void onFailure(Call<Etudiant> call, Throwable t) {

                                }
                            });
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);

                        }else{
                            Log.d("LoginActivity", "connection failure");
                        }
                    }
                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Log.d("LoginActivity", "request failure");
                    }
                });
            }
        });
        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, InscriptionActivity.class);
                startActivity(intent);


            }
        });

    }
    private void setStyle(){
        TextView titre = (TextView) findViewById(R.id.Codification);
        titre.setTypeface(existence_light);

    }
    protected void  bdialog (String mess) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
