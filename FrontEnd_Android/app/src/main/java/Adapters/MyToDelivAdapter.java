package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hajar.firsttry.R;

import java.util.List;

import entities.Commande;
import interfaces.ApiCommandInterface;

/**
 * Created by Hajar on 15/04/2018.
 */

public class MyToDelivAdapter extends RecyclerView.Adapter<MyToDelivAdapter.MyViewHolder> {

private List<Commande> commandes;
private Context context;
private int state;
//private List<LigneCommande> lignes;

public MyToDelivAdapter(List<Commande> commandes, Context context) {
        this.commandes = commandes;
        this.context = context;

        }


@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commandeslivreurs, parent, false);
        return new MyViewHolder(view);


        }

@Override
public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.idcommandelivr.setText(Integer.toString(commandes.get(position).getIdcommande()));
    holder.adressecli.setText(commandes.get(position).getAdresse_livraison());
    holder.nomcli.setText(commandes.get(position).getNom_livraison());


        }

@Override
public int getItemCount() {
        return commandes.size();
        }

public  class MyViewHolder extends RecyclerView.ViewHolder {
    TextView idcommandelivr,adressecli,nomcli;
    Button delete;
    private ApiCommandInterface apiInterface;

    public MyViewHolder(View itemView) {
        super(itemView);

        idcommandelivr = (TextView) itemView.findViewById(R.id.idcommandelivr);
        adressecli = (TextView) itemView.findViewById(R.id.adressecli);
        nomcli= (TextView) itemView.findViewById(R.id.nomcli);

    }

}


}
