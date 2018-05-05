package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hajar.firsttry.R;

import java.util.List;

import entities.LigneCommande;

/**
 * Created by Hajar on 10/04/2018.
 */

public class LignesRecyclerAdapter extends RecyclerView.Adapter<LignesRecyclerAdapter.MyViewHolder>  {


    private List<LigneCommande> lignes;
    private Context context;

    public LignesRecyclerAdapter(List<LigneCommande> lignes, Context context) {
        this.lignes = lignes;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lignes,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.idligne.setText("Ligne Id: "+ Integer.toString(lignes.get(position).getIdligne()));
    }

    @Override
    public int getItemCount() {
        return lignes.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView idligne;
        TextView nomproduit;
        TextView qtproduit;

        public MyViewHolder(View itemView) {
            super(itemView);
            idligne= (TextView) itemView.findViewById(R.id.idligne);
            nomproduit= (TextView) itemView.findViewById(R.id.nomproduit);
            qtproduit= (TextView) itemView.findViewById(R.id.qtproduit);
        }
    }
}
