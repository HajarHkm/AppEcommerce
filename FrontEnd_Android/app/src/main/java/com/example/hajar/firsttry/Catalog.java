package com.example.hajar.firsttry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Adapters.ProduitRecyclerAdapter;
import entities.Client;
import entities.Commande;
import entities.LigneCommande;
import entities.Produit;
import interfaces.ApiClientInterface;
import interfaces.ApiCommandInterface;
import interfaces.ApiProduitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;

public class Catalog extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Produit> produits;
    private List<LigneCommande> lignes;
    private Commande commande;
    private ProduitRecyclerAdapter adapter;

    private ApiProduitInterface apiInterface;
    private ApiCommandInterface commandInterfaceInterface;
    String idclient;
    Button but_confirm_com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        but_confirm_com=(Button) findViewById(R.id.but_confirm_com);
        but_confirm_com.setOnClickListener( Catalog.this);


//need sharedpref
            String category= getIntent().getExtras().getString("category");
             idclient= getIntent().getExtras().getString("idclient");
            System.out.println("session client: "+idclient);
            fetchInformation(category);




    }


    public void fetchInformation(String category)
    {
        apiInterface= ApiClient.getApiClient().create(ApiProduitInterface.class);
        Call<List<Produit>> call  = apiInterface.getcatalog(category);
        call.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                produits = response.body();
                adapter = new ProduitRecyclerAdapter(produits,Catalog.this);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.but_confirm_com)
        {


            System.out.println("confirm clicked");
            String[] qts= adapter.getQts();
            String[] ids= new String[qts.length];
            int [] quantites= new int[qts.length];


            for(int i=0;i<qts.length;i++)
            {
                // ids[i]=((TextView) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.idproduit)).getText().toString();
            }

            for (int i=0;i<qts.length;i++)
            {
                if(Integer.parseInt(qts[i]) != 0) {
                 //   quantites[i] = Integer.parseInt(qts[i]);

                }

            }
            System.out.println("id du premier "+ids[0]);

            System.out.println("quantite du 1er prod "+quantites[0]);


           /* commandInterfaceInterface = ApiClient.getApiClient().create(ApiCommandInterface.class);
            Call<Commande> call = commandInterfaceInterface.addcom(1);
            call.enqueue(new Callback<Commande>() {
                @Override
                public void onResponse(Call<Commande> call, Response<Commande> response) {

                    System.out.println("*******in on response:::" + response.body());
                    Commande commande = response.body();
                    System.out.println("idclient de la commande: " + commande.getIdclient());
                    System.out.println("idcommande: " + commande.getIdcommande());
                    Intent toy = new Intent(Catalog.this, Home.class);

                    startActivity(toy);
                }

                @Override
                public void onFailure(Call<Commande> call, Throwable t) {
                    System.out.println("*******in on failure:::" + t.getMessage().toString() + "and call is: " + call.request().toString());
                }
            });*/


        }


    }


}
