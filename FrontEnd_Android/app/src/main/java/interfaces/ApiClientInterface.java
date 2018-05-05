package interfaces;

import entities.Client;
import entities.Livreur;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hajar on 08/03/2018.
 */

public interface ApiClientInterface {

    @GET("login.php")
    Call<Client> login(@Query("login") String login,@Query("mdp") String mdp);

    @GET("register.php")
    Call<Client> register(@Query("login") String login,@Query("mdp") String mdp,@Query("email") String email);


    @GET("loginLiv.php")
    Call<Livreur> loginLiv(@Query("login") String login, @Query("mdp") String mdp);
}
