package com.example.hajar.firsttry;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Adapters.CommandesRecyclerAdapter;
import count.Utils;
import entities.Commande;
import interfaces.ApiCommandInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;

public class DeliverHome extends AppCompatActivity {

    ArrayAdapter<CharSequence> adapter,adapter2;
    private CommandesRecyclerAdapter adapt,adapt2;
    private RecyclerView recyclerView,recyclerViewdelivering;
    private RecyclerView.LayoutManager layoutManager,layoutManager2;
    int mNotificationsCount=0;
    private List<Commande> commandes;
    private TextView mTextMessage;
    private ApiCommandInterface apiInterface;
    int idlivreur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_home);

        mTextMessage = (TextView) findViewById(R.id.message);


        adapter= ArrayAdapter.createFromResource(DeliverHome.this,R.array.commandes,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter2= ArrayAdapter.createFromResource(DeliverHome.this,R.array.commandes,android.R.layout.simple_spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerVieew);
        //recyclerViewdelivering= (RecyclerView) findViewById(R.id.recyclerViewdelivering);

        layoutManager = new LinearLayoutManager(DeliverHome.this);
        //layoutManager2=new LinearLayoutManager(DeliverHome.this);

        //recyclerViewdelivering.setLayoutManager(layoutManager2);
        //recyclerViewdelivering.setHasFixedSize(true);


        Bundle extras;

        extras = getIntent().getExtras();
        idlivreur = extras.getInt("idlivreur");
        System.out.println("client id i got in deliverhome: "+ idlivreur);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fetchInformation();

    }


    public void fetchInformation()
    {
        apiInterface= ApiClient.getApiClient().create(ApiCommandInterface.class);
        Call<List<Commande>> call  = apiInterface.getLivcommandes(1,"delivering");
        call.enqueue(new Callback<List<Commande>>() {
            @Override
            public void onResponse(Call<List<Commande>> call, Response<List<Commande>> response) {
                commandes = response.body();

                adapt = new CommandesRecyclerAdapter(commandes, DeliverHome.this);
                recyclerView.setAdapter(adapt);


            }

            @Override
            public void onFailure(Call<List<Commande>> call, Throwable t) {

            }
        });
    }


    public void onCheckboxClicked(View view){
        System.out.println("imma assign a job on check clicked ");
        //remove from recycler and change idlivreur and state to delivering
        apiInterface= ApiClient.getApiClient().create(ApiCommandInterface.class);
        Call<Commande> call1  = apiInterface.iWillDeliv(17,idlivreur);
        call1.enqueue(new Callback<Commande>() {
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {
                Commande commande = response.body();
                System.out.println("in on response to assign a job ");
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {

            }
        });
    }


}
