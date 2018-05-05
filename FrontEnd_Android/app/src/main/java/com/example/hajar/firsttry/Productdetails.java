package com.example.hajar.firsttry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.NumberFormat;import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import data.BackGroundTask;
import data.Cart;
import data.CartOperations;

public class Productdetails extends AppCompatActivity {

    Context context=this;

    ImageView productImage;
    TextView description;
    TextView price,sonid;
    TextView quantity_text_view;
    TextView cost_text_view;
    private int mNotificationsCount = 0;
    private SQLiteDatabase mDb;


    String product_image,product_name,product_price,product_quantity,product_rating,product_id;

    Button cart_button;

    int prix;
    private int mQuantity = 1;
    private int mTotalPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CartOperations dbHelper = new CartOperations(this);
        mDb = dbHelper.getWritableDatabase();

        setContentView(R.layout.activity_productdetails);
        productImage = (ImageView) findViewById(R.id.productImage);

        Glide.with(this)
                .load(getIntent().getStringExtra("image"))
                .into(productImage);

        //Bundle extras = getIntent().getExtras();
        //Bitmap bmp = (Bitmap) extras.getParcelable("image");

        //productImage.setImageBitmap(bmp );


        description =(TextView) findViewById(R.id.description);
        price =(TextView) findViewById(R.id.price);
        sonid=(TextView) findViewById(R.id.sonid) ;
        quantity_text_view =(TextView) findViewById(R.id.quantity_text_view);
        cost_text_view =(TextView) findViewById(R.id.cost_text_view);
        description.setText(getIntent().getStringExtra("nom"));
        price.setText(String.valueOf(getIntent().getExtras().getInt("prix")));

        System.out.println("*****second"+getIntent().getStringExtra("id"));
        sonid.setText(String.valueOf(getIntent().getStringExtra("id")));


       /* RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingLevel);
        ratingBar.setRating(2); // should actually get rating from cart table*/


        cart_button =(Button) findViewById(R.id.cart_button);
        new FetchCountTask().execute();
    }

    BackGroundTask backGroundTask=new BackGroundTask(this);
    public void addToCart(View view){

        product_image=getIntent().getStringExtra("image");
        product_name=description.getText().toString();
        product_price=String.valueOf(price.getText().toString());
        product_quantity= quantity_text_view.getText().toString();
        product_rating=String.valueOf(2); //should get it from table
        product_id=sonid.getText().toString();


        backGroundTask.execute("add_product",product_image,product_name,product_price,product_quantity,product_rating,product_id);
        finish();


    }



    public void increment(View view){

        prix = getIntent().getExtras().getInt("prix");
        mQuantity = mQuantity + 1;
        displayQuantity(mQuantity);
        mTotalPrice = mQuantity * prix;
        displayCost(mTotalPrice);
    }

    public void decrement(View view){
        if (mQuantity > 1){

            mQuantity = mQuantity - 1;
            displayQuantity(mQuantity);
            mTotalPrice = mQuantity * prix;
            displayCost(mTotalPrice);

        }
    }

    private void displayQuantity(int numberOfItems) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfItems));
    }

    private void displayCost(double totalPrice) {

        //String convertPrice = NumberFormat.getCurrencyInstance().format(totalPrice);
        cost_text_view.setText(String.valueOf(totalPrice));
    }

    private void updateNotificationsBadge(int count) {
        mNotificationsCount = count;

        // force the ActionBar to relayout its MenuItems.
        // onCreateOptionsMenu(Menu) will be called again.
        invalidateOptionsMenu();
    }

    /*
Sample AsyncTask to fetch the notifications count
*/
    class FetchCountTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            String countQuery = "SELECT  * FROM " + Cart.TableInfo.table_name;
            Cursor cursor = mDb.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();
            return count;

        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }

}
