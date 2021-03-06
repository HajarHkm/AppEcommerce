package com.example.hajar.firsttry;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import data.BackGroundTask;
import data.CartAdapter;
import data.Cartclass;
import dialogs.ConfirmationDialog;
import dialogs.UpdateDialog;
import entities.Commande;
import entities.LigneCommande;
import interfaces.ApiCommandInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;

public class Cartdb extends AppCompatActivity implements UpdateDialog.UpdateDialogListener, ConfirmationDialog.ConfirmationDialogListener{

    TextView totalPrice;
    ListView listview;
    CartAdapter cartAdapter;
    Button button_cart;
    private String nomlivraison,prenomlivraison,tellivraison,adresselivraison;
    private ApiCommandInterface commandeInterface;
    private Commande commande;
    private LigneCommande ligne;
    int total,idclient,idcommande;
    int[] ids,quantits;

    int updatedQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

       // totalPrice=(TextView) findViewById(R.id.totalPrice);

       listview= (ListView) findViewById(R.id.cart_list);

       cartAdapter = new CartAdapter(this, R.layout.cart_item);

        final BackGroundTask backGroundTask=new BackGroundTask(this);
        backGroundTask.execute("get_products");





        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AdapterView adapt=adapterView;
                final View viw=view;
               System.out.println("suup loong click  "+listview.getAdapter().getItem(i) );
                System.out.println("suup id  "+listview.getAdapter().getItemId(i) );
                System.out.println("suup loong click  "+adapterView.getItemAtPosition(i));

                final Cartclass obj = (Cartclass) listview.getAdapter().getItem(i);
               // String id = (String) obj.get("product_id");
                Log.d("the idd i waaant", String.valueOf(obj.getProduct_id()));
                //adapterView.removeViewAt(i);

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        Cartdb.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete this Item from your cart");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapt.removeViewInLayout(viw);
                        final BackGroundTask backGroundTaskk=new BackGroundTask(Cartdb.this);
                        backGroundTaskk.execute(String.valueOf(obj.getProduct_id()));

                        BackGroundTask backGroundTask=new BackGroundTask(Cartdb.this);
                        backGroundTask.execute("get_products");
                    }
                });


                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
                alert.show();


                return false;

            }
        });


        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UpdateDialog updateDialog= new UpdateDialog();
                updateDialog.show(getSupportFragmentManager(),"updateDialog");
                final Cartclass obj1 = (Cartclass) listview.getAdapter().getItem(i);
                int id= obj1.getProduct_id();
                updatedQuantity=3;
                obj1.setProduct_quantity(updatedQuantity);
                Log.d("the idd i waaant", String.valueOf(obj1.getProduct_id()));
                BackGroundTask backGroundTask=new BackGroundTask(Cartdb.this);
                backGroundTask.execute("get_products");


            }
        });*/

        gettotal();



    }



    public Void gettotal(){
        button_cart= (Button)findViewById(R.id.button_cart);
        button_cart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int sum = 0;
                int[] id_produits_commandes= new int[listview.getAdapter().getCount()];
                int[] quantities= new int[listview.getAdapter().getCount()];

                for (int i = 0; i < listview.getAdapter().getCount();i++)
                {
                    view = listview.getChildAt(i);
                    TextView product_id = (TextView) view.findViewById(R.id.product_id);
                    id_produits_commandes[i]=Integer.parseInt(product_id.getText().toString());


                    TextView Cartquantity = (TextView) view.findViewById(R.id.Cartquantity);
                    quantities[i]=Integer.parseInt(Cartquantity.getText().toString());


                    TextView Cartprice = (TextView) view.findViewById(R.id.Cartprice);
                    int price = Integer.parseInt(Cartprice.getText().toString());
                    int quantity = Integer.parseInt(Cartquantity.getText().toString());
                    sum = sum + price * quantity;


                    System.out.println("ids des produits commandés: "+id_produits_commandes[i]);
                }
                ids=id_produits_commandes;
                quantits=quantities;
                //confirmation dialog and get new info
                ConfirmationDialog confirmationDialog= new ConfirmationDialog();
                confirmationDialog.show(getSupportFragmentManager(),"confirmationDialog");
            }
        });

        return null;
    }

    @Override
    public void applyTexts(String prenom, String nom, String adresse, String tel) {
        nomlivraison=nom;
        prenomlivraison= prenom;
        adresselivraison=adresse;
        tellivraison=tel;
        System.out.println("new adresse i got in homepage: "+ adresselivraison);
        Bundle extras;
        extras = getIntent().getExtras();
        idclient = extras.getInt("idclient");
        System.out.println("client id i got in homepage: "+ idclient);

        //add new commande
        commandeInterface = ApiClient.getApiClient().create(ApiCommandInterface.class);
        Call<Commande> call = commandeInterface.addcom(idclient,adresse,nom,prenom,tel);
        call.enqueue(new Callback<Commande>() {
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {

                System.out.println("*******in on response:::" + response.body());
                commande = response.body();
                idcommande=commande.getIdcommande();
                System.out.println("idproduit " + ids[0]);
                System.out.println("idcommande enregistree: " + commande.getIdcommande());
                for(int i=0;i<ids.length;i++) {
                    Call<LigneCommande> call2 = commandeInterface.addligne(idcommande, ids[i], quantits[i]);
                    call2.enqueue(new Callback<LigneCommande>() {
                        @Override
                        public void onResponse(Call<LigneCommande> call, Response<LigneCommande> response) {

                            System.out.println("*******in on response:::" + response.body());
                            ligne = response.body();
                            System.out.println("idligne enregistree: " + ligne.getIdligne());

                            //Intent toy = new Intent(Home.this, Basket.class);
                            //startActivity(toy);
                        }

                        @Override
                        public void onFailure(Call<LigneCommande> call, Throwable t) {
                            System.out.println("*******in on failure:::" + t.getMessage().toString() + "and call is: " + call.request().toString());
                        }
                    });
                }
                BackGroundTask backGroundTask=new BackGroundTask(Cartdb.this);
                backGroundTask.execute("deleteall");
                finish();
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {
                System.out.println("*******in on failure:::" + t.getMessage().toString() + "and call is: " + call.request().toString());
            }
        });

        //and add the new lignes


        }

    @Override
    public void getUpdates(int quantity) {
      updatedQuantity= quantity;

    }
}
