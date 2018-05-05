package webService;

/**
 * Created by Hajar on 21/02/2018.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import entities.Client;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;

import android.util.Log;

public class getClient{

    String url;
    LinkedList<Client> listeClients=new LinkedList<Client>();
    Client cli=new Client();



    public getClient(String url, Activity a){
        this.url=url;

        intClients(a);
        //intProduits(a);
        //intCommandes(a);
    }


    public boolean phpCall(String login, String mdp){

        return true;
    }




    public void intClients(Activity a){

        //RequestQueue queue = Volley.newRequestQueue(a);
        System.out.println("im here");

        JsonArrayRequest jsArrRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {




                    @Override
                    public void onResponse( JSONArray  response) {
                        Log.d("TAG", "response is"+ response.toString());
                        System.out.println("the response: "+response);
                        try {
                            for(int i=0;i<response.length();i++) {

                                Client c=new Client();

                                JSONObject object = response.getJSONObject(i);

                                c.setAdressecli(object.getString("adressecli"));

                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                                c.setDatenaissance(format.parse(object.getString("datenaissance")));
                                c.setEmail(object.getString("email"));
                                c.setIdclient(object.getInt("idclient"));
                                c.setLoginclient(object.getString("loginclient"));
                                c.setNomclient(object.getString("nomclient"));
                                c.setPrenomclient(object.getString("prenomclient"));
                                c.setMdpclient(object.getString("mdpclient"));
                                c.setTelclient(object.getString("telclient"));

                                //object.getJSONArray("evaluationCollection");
                                //object.getJSONObject("commandeCollection");
                                System.out.println("normally added");

                                listeClients.add(c);

                            }
                            System.out.println("should not be null" +listeClients);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("first catch exception");

                        } catch (ParseException e) {
                            e.printStackTrace();
                            System.out.println("second catch exception");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof TimeoutError || error instanceof NoConnectionError)
                        {
                            System.out.println("************again:" +error);

                        }
                        else if (error instanceof AuthFailureError)
                        {
                            System.out.println("************again:" +error);
                        }
                        else if(error instanceof ServerError)
                        {
                            System.out.println("************again:" +error);
                        }
                        else if(error instanceof NetworkError)
                        {
                            System.out.println("************again:" +error);
                        }
                        else if(error instanceof ParseError)
                        {
                            System.out.println("************again:" +error);
                        }
                        else
                            System.out.println("************new:" +error);


                    }



                });

        /*jsArrRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        jsArrRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(a);
        queue.add(jsArrRequest);


        System.out.println("the request is: "+jsArrRequest);
    }


    public LinkedList<Client> getAllClients(){
        return listeClients;
    }

    public boolean isClient(String log, String pass){
        if(listeClients != null){

            System.out.println("searching for: "+log+"and"+pass);
            System.out.println("no clients");
        }
        for(Client c : listeClients ){
            System.out.println("theres a client");
            if (c.getLoginclient().equals(log) && c.getMdpclient().equals(pass)){
                System.out.println("client" + c.getLoginclient());
                return true;
            }
            else
                System.out.println("client not found");
        }
        return false;
    }


}
