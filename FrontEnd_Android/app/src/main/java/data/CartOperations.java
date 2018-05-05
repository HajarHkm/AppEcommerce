package data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hajar.firsttry.Cartdb;

import data.Cart;

/**
 * Created by Hajar on 12/04/2018.
 */

public class CartOperations extends SQLiteOpenHelper {

    public static final int db_version=1;


    public String create_query = "CREATE TABLE "+Cart.TableInfo.table_name+" (product_id INTEGER, product_name TEXT, product_image TEXT,product_price INTEGER,product_quantity INTEGER, product_rating INTEGER);";


    public CartOperations(Context context) {
        super(context, Cart.TableInfo.DB_NAME, null, db_version);
        Log.d("Db operations","Db created "+Cart.TableInfo.DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("heere"+create_query);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Cart.TableInfo.table_name);
        Log.d("query",create_query);
        sqLiteDatabase.execSQL(create_query);
        Log.d("Db operations","table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void putInformation(CartOperations cartOp, String name,String image, int price, int quantity, int rating,int id){
        SQLiteDatabase sq = cartOp.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("product_name",name);
        cv.put("product_image",image);
        cv.put("product_price",price);
        cv.put("product_quantity",quantity);
        cv.put("product_rating",rating);
        cv.put("product_id",id);
        long k=sq.insert("Mycart",null,cv);
        Log.d("Db operations","product added to chart");

    }

    public Cursor getInformation(CartOperations cartOp)
    {
        SQLiteDatabase sq = cartOp.getReadableDatabase();
        String columns[]= {"product_name", "product_image","product_price", "product_quantity" , "product_rating", "product_id"};
        Cursor cr=sq.query("Mycart",columns,null,null,null,null,null);
        return cr;
    }


    public void delete(int product_id)
    {
        String[] args={String.valueOf(product_id)};
        getWritableDatabase().delete("Mycart", "product_id=?", args);

    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Cart.TableInfo.table_name,null,null);
        db.execSQL("delete from "+ Cart.TableInfo.table_name);
        //db.execSQL("TRUNCATE table" + Cart.TableInfo.table_name);
        db.close();
    }


}
