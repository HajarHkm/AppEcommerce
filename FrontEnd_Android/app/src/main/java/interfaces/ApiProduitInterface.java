package interfaces;

import java.util.List;

import entities.Produit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hajar on 08/03/2018.
 */

public interface ApiProduitInterface {

    @GET("getcatalog.php")
    Call<List<Produit>> getcatalog(@Query("category") String category);


}
