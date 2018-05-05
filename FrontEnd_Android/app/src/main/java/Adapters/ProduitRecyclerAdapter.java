package Adapters;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hajar.firsttry.Productdetails;
import com.example.hajar.firsttry.R;

import java.util.LinkedList;
import java.util.List;

import entities.Client;
import entities.LigneCommande;
import entities.Produit;

import static android.view.View.DRAWING_CACHE_QUALITY_HIGH;

/**
 * Created by Hajar on 09/03/2018.
 */

public class ProduitRecyclerAdapter extends RecyclerView.Adapter<ProduitRecyclerAdapter.MyViewHolder> {

    private List<Produit> produits;
    private Context context;
   // private List<LigneCommande> lignes;
    private String[] qts;
    private String[] ids;


    public ProduitRecyclerAdapter(List<Produit> produits, Context context) {
        this.produits = produits;
        this.context = context;
        this.qts=new String[produits.size()];
        this.ids= new String[produits.size()];

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.products,parent,false);

        return new MyViewHolder(view,produits,context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.nomproduit.setText(produits.get(position).getNomproduit());
        holder.sonid.setText(Integer.toString(produits.get(position).getIdproduit()));
        //holder.price.setText("Price: "+ Integer.toString(produits.get(position).getPrix()));
        //holder.idproduit.setText("Id: "+ Integer.toString(produits.get(position).getIdproduit()));
        System.out.println("image path "+produits.get(position).getImageproduit());
        Glide.with(context).load(produits.get(position).getImageproduit()).into(holder.image);




    }

    @Override
    public int getItemCount() {
        return produits.size();

    }

    public String[] getQts() {
        return qts;
    }

    public String[] getIds() {
        return ids;
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image ;
        TextView nomproduit,price,sonid;
         EditText quantit;

        List<Produit> produits;
        Context context;

        public  MyViewHolder(View itemView, List<Produit> produits, Context context) {
            super(itemView);

            this.produits=produits;
            this.context=context;

            itemView.setOnClickListener(this);


            nomproduit= (TextView) itemView.findViewById(R.id.product);
            image=(ImageView) itemView.findViewById(R.id.imageView);
            sonid=(TextView) itemView.findViewById(R.id.sonid);
            price= (TextView) itemView.findViewById(R.id.price);

            /*quantit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    qts[getAdapterPosition()]= charSequence.toString();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });*/

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Produit produit=this.produits.get(position);

            Intent toy= new Intent(context, Productdetails.class);
            toy.putExtra("id",sonid.getText().toString());
            toy.putExtra("nom",produit.getNomproduit());
            toy.putExtra("prix",produit.getPrix());
            System.out.println("fiiirst"+sonid.getText().toString());
            toy.putExtra("image",produit.getImageproduit());
            this.context.startActivity(toy);
        }
    }
}
