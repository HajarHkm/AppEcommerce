package com.example.hajar.firsttry;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import Adapters.ProduitRecyclerAdapter;
import data.Cart;
import data.CartOperations;
import dialogs.ConfirmationDialog;
import entities.Commande;
import entities.Produit;
import interfaces.ApiCommandInterface;
import interfaces.ApiProduitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;
import count.Utils;
import static java.lang.Thread.sleep;

//import static data.FragranceContract.FragranceEntry.CART_TABLE;

public class Home extends AppCompatActivity implements ConfirmationDialog.ConfirmationDialogListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Produit> produits;

    private Commande commande;
    private String nomlivraison,prenomlivraison,tellivraison,adresselivraison;
    private ApiCommandInterface commandeInterface;

    private ProduitRecyclerAdapter adapt;


    private ApiProduitInterface apiInterface;

    int idclient;
    Button but_confirm_com;
    Button butbasket;

    ArrayAdapter<CharSequence> adapter;
    Spinner spinner;
    String spinner_value;
    private int mNotificationsCount = 0;
    private SQLiteDatabase mDb;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        MenuItem item = menu.findItem(R.id.action_notifications);
        LayerDrawable icon = (LayerDrawable) item.getIcon();

        // Update LayerDrawable's BadgeDrawable
        Utils.setBadgeCount(this, icon, mNotificationsCount);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle extras;

        extras = getIntent().getExtras();
        idclient = extras.getInt("idclient");
        System.out.println("client id i got in homepage: "+ idclient);

        switch (item.getItemId()) {

            case R.id.action_notifications: {
                Intent intent = new Intent(this, Cartdb.class);

                intent.putExtra("idclient",idclient);

                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateNotificationsBadge(int count) {
        mNotificationsCount = count;

        // force the ActionBar to relayout its MenuItems.
        // onCreateOptionsMenu(Menu) will be called again.
        invalidateOptionsMenu();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CartOperations dbHelper = new CartOperations(this);
        mDb = dbHelper.getWritableDatabase();


        spinner = (Spinner) findViewById(R.id.cat_spinner);

        adapter=ArrayAdapter.createFromResource(Home.this,R.array.categories_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i)+"selected", Toast.LENGTH_LONG).show();
                spinner_value= (String) adapterView.getItemAtPosition(i);
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(Home.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

                //push into lignes[]

                fetchInformation(spinner_value);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        new FetchCountTask().execute();
        //butbasketClicked();
        //butConfirmClicked();

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_cart_round);
    }


    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        new FetchCountTask().execute();
    }


    public void fetchInformation(String category)
    {


        apiInterface= ApiClient.getApiClient().create(ApiProduitInterface.class);
        Call<List<Produit>> call  = apiInterface.getcatalog(category);
        call.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                produits = response.body();
                adapt = new ProduitRecyclerAdapter(produits,Home.this);
                recyclerView.setAdapter(adapt);

            }

            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {

            }
        });

    }


    @Override
    public void applyTexts(String prenom, String nom, String adresse, String tel) {
            nomlivraison=nom;
            prenomlivraison= prenom;
            adresselivraison=adresse;
            tellivraison=tel;
        System.out.println("new adresse i got in homepage: "+ adresselivraison);

        //add new commande
        commandeInterface = ApiClient.getApiClient().create(ApiCommandInterface.class);
        Call<Commande> call = commandeInterface.addcom(idclient,adresse,nom,prenom,tel);
        call.enqueue(new Callback<Commande>() {
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {

                System.out.println("*******in on response:::" + response.body());
                commande = response.body();
                System.out.println("id client commande enregistree: " + commande.getIdclient());

                //Intent toy = new Intent(Home.this, Basket.class);
                //startActivity(toy);
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {
                System.out.println("*******in on failure:::" + t.getMessage().toString() + "and call is: " + call.request().toString());
            }
        });

        //and add the new lignes




    }




    class FetchCountTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            String countQuery = "SELECT  * FROM "+ Cart.TableInfo.table_name;
            Cursor cursor = mDb.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();
            return count;
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }

}
