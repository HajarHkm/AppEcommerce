package com.example.hajar.firsttry;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import entities.Client;
import interfaces.ApiClientInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    public Button butsignup,butcancel;

    private Client client=new Client() ;
    private ApiClientInterface clientInterface;

    EditText loginclient,mdpclient,nomclient,prenomclient,adressecli,email,mdpconfirm,telclient ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //s=new PostClient("http://192.168.43.237:8080/ecommercev1/webresources/entities.client", this);
        //s=new PostClient("http://10.0.2.2/android/register.php", this);


        butsignup=(Button) findViewById(R.id.butsignup);
        butsignup.setOnClickListener(this);


        loginclient=(EditText) findViewById(R.id.loginclient);
        mdpclient=(EditText) findViewById(R.id.mdpclient);
        nomclient=(EditText) findViewById(R.id.nomclient);
        prenomclient=(EditText) findViewById(R.id.prenomclient);
        adressecli=(EditText) findViewById(R.id.adressecli);
        email=(EditText) findViewById(R.id.email);
        mdpconfirm=(EditText) findViewById(R.id.mdpconfirm);
        telclient=(EditText) findViewById(R.id.telclient);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_cart_round);

    }


    @Override
    public void onClick(View view) {
        System.out.println("in onclick");
        if(view.getId()==R.id.butsignup) {
            if (mdpclient.getText().toString().equals(mdpconfirm.getText().toString())) {
                System.out.println("clientsettings");
                client.setLoginclient(loginclient.getText().toString());
                client.setMdpclient(mdpclient.getText().toString());
                client.setNomclient(nomclient.getText().toString());
                client.setPrenomclient(prenomclient.getText().toString());
                client.setAdressecli(adressecli.getText().toString());
                client.setEmail(email.getText().toString());
                Date date = new Date(2017, 03, 02);
                client.setDatenaissance(date);

                client.setTelclient(telclient.getText().toString());
                System.out.println("client's name" + client.getLoginclient());

                clientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
                    Call<Client> call = clientInterface.register(client.getLoginclient(), client.getMdpclient(), client.getEmail());
                call.enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {

                        System.out.println("*******in on response:::" + response.body());
                        client = response.body();
                        System.out.println("client: " + client.getNomclient());
                        Intent toy = new Intent(SignUp.this, MainActivity.class);

                        startActivity(toy);
                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {
                        System.out.println("*******in on failure:::" + t.getMessage().toString() + "and call is: " + call.request().toString());
                    }
                });

            } else {

                System.out.println("mdp conf not identical");
                Toast.makeText(getBaseContext(),"Passwords don't match",Toast.LENGTH_LONG).show();

            }
        }

    }
}
