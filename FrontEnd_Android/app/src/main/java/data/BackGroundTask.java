package data;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hajar.firsttry.R;

/**
 * Created by Hajar on 13/04/2018.
 */

public class BackGroundTask extends AsyncTask<String,Cartclass,String> {

    Context context;
    CartAdapter productAdapter;
    Activity activity;
    ListView listView;


    public BackGroundTask(Context context) {
        this.context = context;
        this.activity= (Activity) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Cartclass... values) {

        productAdapter.add(values[0]);

    }

    @Override
    protected String  doInBackground(String... params) {
        CartOperations cartOperations=new CartOperations(context);
        String method= params[0];
        if(method.equals("add_product"))
        {

            String product_image,product_name;
            int product_price,product_quantity,product_rating,product_id;
            System.out.println("valuuuuuues*************"+params[0]+params[1]+params[2]+params[3]+params[4]+params[5]);
            product_image=params[1];
            product_name=params[2];
            product_price=Integer.valueOf(params[3]) ;
            product_quantity=Integer.valueOf(params[4]);
            product_rating=Integer.valueOf(params[5]);
            product_id=Integer.valueOf(params[6]);
            System.out.println("valuuuuuues************* product id"+product_id);
            cartOperations.putInformation(cartOperations, product_name,product_image, product_price,product_quantity, product_rating,product_id);
            return "Product Added to cart";
        }
        else if(method.equals("get_products"))
        {
            listView = (ListView) activity.findViewById(R.id.cart_list);

            Cursor cursor=cartOperations.getInformation(cartOperations);
            productAdapter = new CartAdapter(context, R.layout.cart_item);
            String product_image,product_name;
            int product_price,product_quantity,product_rating,product_id;
            while(cursor.moveToNext())
            {
                product_image=cursor.getString(cursor.getColumnIndex("product_image"));
                product_name=cursor.getString(cursor.getColumnIndex("product_name"));
                product_price=cursor.getInt(cursor.getColumnIndex("product_price"));
                product_quantity=cursor.getInt(cursor.getColumnIndex("product_quantity"));
                product_rating=cursor.getInt(cursor.getColumnIndex("product_rating"));
                product_id=cursor.getInt(cursor.getColumnIndex("product_id"));
                Cartclass product =new Cartclass(product_name,product_image,product_rating,product_price,product_quantity,product_id);
                publishProgress(product);
            }


            return "Products";

        }
        else if(method.equals("deleteall"))
        {
            cartOperations.deleteAll();
            return"Successful Order";
        }
        else
        {
            cartOperations.delete(Integer.valueOf(method));
            return "Deleted";
        }

    }

    @Override
    protected void onPostExecute(String  result) {
        if(result.equals("Products"))
        {
            listView.setAdapter(productAdapter);


        }



        else Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }


}
