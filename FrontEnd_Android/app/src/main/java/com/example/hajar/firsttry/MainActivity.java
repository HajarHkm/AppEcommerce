

package com.example.hajar.firsttry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import count.Utils;
import entities.Client;
import entities.Livreur;
import interfaces.ApiClientInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;
import webService.getClient;
import static java.lang.Thread.sleep;


    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        int mNotificationsCount=0;

        Button valider,register;
        EditText utilisateur;
        EditText passe;
        TextView erreur;
        getClient s;
        int cliOrDeliv;
        private Livreur livreur;
        private Client client;
        private ApiClientInterface clientInterface;
        public static final String MY_PREFS_NAME = "MyPrefsFile";



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            valider=(Button) findViewById(R.id.but1);
            valider.setOnClickListener(this);
            register=(Button) findViewById(R.id.but2);
            register.setOnClickListener(this);

            utilisateur=(EditText) findViewById(R.id.login);
            passe=(EditText) findViewById(R.id.pass);
            erreur=(TextView)findViewById(R.id.error);
            erreur.setVisibility(View.INVISIBLE);

            ActionBar actionBar=getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_cart_round);


        }

        public void onClick(View v){

            if (v.getId() == R.id.but1){
                cliOrDeliv=0;
                clientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
                Call<Client> call=clientInterface.login(utilisateur.getText().toString(),passe.getText().toString());

                try {
                    call.enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {
                            if(response != null){

                                cliOrDeliv=1;
                                System.out.println("*******in on response for delivery guy:::" +response.body());
                                client= response.body();
                                System.out.println("client: "+ client.getIdclient());
                                Intent toy = new Intent(MainActivity.this, Home.class);
                                toy.putExtra("idclient",client.getIdclient());
                                startActivity(toy);
                        }}

                        @Override
                        public void onFailure(Call<Client> call, Throwable t) {

                            System.out.println("*******in on failure:::" +t.getMessage().toString()+ "and call is: "+call.request().toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(cliOrDeliv==0)
                {
                    System.out.println("i am at deliveryyyyy");
                    Call<Livreur> calldeliv=clientInterface.loginLiv(utilisateur.getText().toString(),passe.getText().toString());

                    try {
                        calldeliv.enqueue(new Callback<Livreur>() {
                            @Override
                            public void onResponse(Call<Livreur> call, Response<Livreur> response) {
                                if(response != null){

                                    cliOrDeliv=1;
                                    System.out.println("*******in on response:::" +response.body());
                                    livreur= response.body();
                                    System.out.println("client: "+ livreur.getIdlivreur());
                                    Intent toy = new Intent(MainActivity.this, MyToDeliv.class);
                                    toy.putExtra("idlivreur",livreur.getIdlivreur());
                                    startActivity(toy);
                                }}

                            @Override
                            public void onFailure(Call<Livreur> call, Throwable t) {



                                System.out.println("*******in on failure:::" +t.getMessage().toString()+ "and call is: "+call.request().toString());
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        erreur.setVisibility(View.VISIBLE);
                    }

                }
            }
            else if (v.getId() == R.id.but2){
                System.out.println("should go to signuup now");
                Intent toy = new Intent(MainActivity.this, SignUp.class);
                startActivity(toy);
            }

        }




}
