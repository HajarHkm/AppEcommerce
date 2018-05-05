package com.example.hajar.firsttry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Adapters.CommandesRecyclerAdapter;
import Adapters.ProduitRecyclerAdapter;
import entities.Commande;
import entities.LigneCommande;
import entities.Produit;
import interfaces.ApiCommandInterface;
import interfaces.ApiProduitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;

public class Basket extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CommandesRecyclerAdapter adapt;
    ArrayAdapter<CharSequence> adapter;
    Spinner spinner;
    String spinner_value;
    private ApiCommandInterface apiInterface;
    private List<Commande> commandes;
    private List<LigneCommande> lignes;


    @Override
    protected void onRestart() {
        super.onRestart();
        fetchInformation(spinner_value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        spinner = (Spinner) findViewById(R.id.com_spinner);

        adapter=ArrayAdapter.createFromResource(Basket.this,R.array.commandes,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i)+"selected", Toast.LENGTH_LONG).show();

                spinner_value= (String) adapterView.getItemAtPosition(i);

                recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCom);
                layoutManager = new LinearLayoutManager(Basket.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

                //adapt = new CommandesRecyclerAdapter(commandes, Basket.this);
                //recyclerView.setAdapter(adapt);


                fetchInformation(spinner_value);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void fetchInformation(String state)
    {

        apiInterface= ApiClient.getApiClient().create(ApiCommandInterface.class);
        Call<List<Commande>> call  = apiInterface.getcommandes(1,state);
        call.enqueue(new Callback<List<Commande>>() {
            @Override
            public void onResponse(Call<List<Commande>> call, Response<List<Commande>> response) {
                commandes = response.body();
                adapt = new CommandesRecyclerAdapter(commandes, Basket.this);
                recyclerView.setAdapter(adapt);
            }

            @Override
            public void onFailure(Call<List<Commande>> call, Throwable t) {

            }
        });



    }
}
