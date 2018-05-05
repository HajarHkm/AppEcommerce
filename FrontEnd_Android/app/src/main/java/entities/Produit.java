package entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hajar on 08/03/2018.
 */

public class Produit {

    @SerializedName("idproduit")
    int idproduit;
    @SerializedName("idcatalogue")
    int idcatalogue;
    @SerializedName("nomproduit")
    String nomproduit;
    @SerializedName("prix")
    int prix;
    @SerializedName("typeproduit")
    String typeproduit;

    @SerializedName("imageproduit")
    String imageproduit;

    public String getImageproduit() {
        return imageproduit;
    }

    public void setImageproduit(String imageproduit) {
        this.imageproduit = imageproduit;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIdcatalogue() {
        return idcatalogue;
    }

    public void setIdcatalogue(int idcatalogue) {
        this.idcatalogue = idcatalogue;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
    }
}
