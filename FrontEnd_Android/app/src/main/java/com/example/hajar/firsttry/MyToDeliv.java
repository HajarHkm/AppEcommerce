package com.example.hajar.firsttry;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Adapters.CommandesRecyclerAdapter;
import Adapters.MyToDelivAdapter;
import Adapters.ProduitRecyclerAdapter;
import count.Utils;
import data.BackGroundTask;
import data.Cartclass;
import entities.Commande;
import entities.Produit;
import interfaces.ApiCommandInterface;
import interfaces.ApiProduitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;

public class MyToDeliv extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyToDelivAdapter adapt;
    private List<Commande> commandes;
    int idlivreur;
    ListView listview;

    int mNotificationsCount=0;

    private ApiCommandInterface apiInterface;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_to_deliv);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerVieew1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Bundle extras;

        extras = getIntent().getExtras();
        idlivreur = extras.getInt("idlivreur");
        System.out.println("client id i got in mytodeliver: "+ idlivreur);
        fetchInformation();

        recyclerView.removeOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_cart_round);

    }

    public void fetchInformation()
    {
        apiInterface= ApiClient.getApiClient().create(ApiCommandInterface.class);
        Call<List<Commande>> call  = apiInterface.getLivcommandes(idlivreur,"delivering");
        call.enqueue(new Callback<List<Commande>>() {
            @Override
            public void onResponse(Call<List<Commande>> call, Response<List<Commande>> response) {
                commandes = response.body();

                adapt = new MyToDelivAdapter(commandes, MyToDeliv.this);
                recyclerView.setAdapter(adapt);


            }

            @Override
            public void onFailure(Call<List<Commande>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);

        // Get the notifications MenuItem and
        // its LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.action_mydeliv);
        //LayerDrawable icon = (LayerDrawable) item.getIcon();


        BitmapDrawable iconBitmap = (BitmapDrawable) item.getIcon();
        LayerDrawable iconLayer = new LayerDrawable(new Drawable[] { iconBitmap });

        // Update LayerDrawable's BadgeDrawable
        //Utils.setBadgeCount(this, icon, mNotificationsCount);
        Utils.setBadgeCount(this, iconLayer, mNotificationsCount);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_mydeliv: {
                Intent intent = new Intent(this, DeliverHome.class);
                intent.putExtra("idlivreur",idlivreur);
                startActivity(intent);
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void onCheckboxClicked(View view){

    }

}
