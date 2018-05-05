package data;

import android.provider.BaseColumns;

/**
 * Created by Hajar on 12/04/2018.
 */

public final class Cart {

    public Cart() {
    }

    public static abstract class TableInfo implements BaseColumns

    {
        public final String product_id ="id";
        public final String product_name ="name";
        public final String product_image="image";
        public final String product_rating="rating";
        public final String product_price="price";
        public final String product_quantity="quantity";
        public static final String DB_NAME="mycart_db";
        public static final String table_name="mycart";

    }
}
