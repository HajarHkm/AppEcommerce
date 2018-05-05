package Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajar.firsttry.Basket;
import com.example.hajar.firsttry.R;

import java.util.List;

import entities.Commande;
import entities.LigneCommande;
import interfaces.ApiCommandInterface;
import interfaces.ApiCommandInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.ApiClient;

/**
 * Created by Hajar on 09/04/2018.
 */

public class CommandesRecyclerAdapter extends RecyclerView.Adapter<CommandesRecyclerAdapter.MyViewHolder> {

    private List<Commande> commandes;
    private Context context;
    private int state;

    private ApiCommandInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyToDelivAdapter adapt;
    //private List<LigneCommande> lignes;

    public CommandesRecyclerAdapter(List<Commande> commandes, Context context) {
        this.commandes = commandes;
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commandes, parent, false);
            return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {
     holder.idcommande.setText(Integer.toString(commandes.get(position).getIdcommande()));
        holder.adressecl.setText(commandes.get(position).getAdresse_livraison());
        holder.nomclient.setText(commandes.get(position).getNom_livraison() );
        holder.idcli.setText(Integer.toString(commandes.get(position).getIdclient()));



    }

    @Override
    public int getItemCount() {
        return commandes.size();
}

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idcommande,nomclient,adressecl,idcli;
        Button deliveer;
        CheckBox checkbox_willdelivered;



        public MyViewHolder(View itemView) {
            super(itemView);

            idcommande= (TextView) itemView.findViewById(R.id.idcommande);
            adressecl= (TextView) itemView.findViewById(R.id.adressecl);
            nomclient = (TextView) itemView.findViewById(R.id.nomclient);
            idcli= (TextView) itemView.findViewById(R.id.idcli);
            checkbox_willdelivered = (CheckBox) itemView.findViewById(R.id.checkbox_willdelivered);



        }



    }




}
