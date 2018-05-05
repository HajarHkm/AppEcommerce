package interfaces;

import java.util.List;

import entities.Client;
import entities.Commande;
import entities.LigneCommande;
import entities.Produit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hajar on 19/03/2018.
 */

public interface ApiCommandInterface {

    @GET("addcom.php")
    Call<Commande> addcom(@Query("idclient") int idclient,@Query("adresse") String adresse_liv,@Query("nom") String nom_liv,@Query("prenom") String prenom_liv,@Query("tel") String tel_liv);

    @GET("getcommandes.php")
    Call<List<Commande>> getcommandes(@Query("idlivreur") int idlivreur,@Query("state") String state);



    @GET("getLivcommandes.php")
    Call<List<Commande>> getLivcommandes(@Query("idlivreur") int idlivreur,@Query("state") String state);

    @GET("addligne.php")
    Call<LigneCommande> addligne(@Query("idcommande") int idcommande,@Query("idproduit") int idproduit,@Query("quantity") int quantity);

    @GET("deletecom.php")
    Call<String> deletecom(@Query("idcommande") int idcommande);


    @GET("iWillDeliver.php")
    Call<Commande> iWillDeliv(@Query("idcommande") int idcommande,@Query("idlivreur") int idlivreur);

}
