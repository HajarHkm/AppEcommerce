package data;

/**
 * Created by Hajar on 13/04/2018.
 */

public class Cartclass {

    public String product_name;
    public  String product_image;
    public  int product_rating;
    public  int product_price;
    public  int product_quantity;
    public int product_id;


    public Cartclass(String product_name, String product_image, int product_rating, int product_price, int product_quantity,int product_id) {
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_rating = product_rating;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_id=product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public int getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(int product_rating) {
        this.product_rating = product_rating;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }
}
