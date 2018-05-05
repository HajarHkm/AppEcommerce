package entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hajar on 13/04/2018.
 */

public class Livreur {

    @SerializedName("idlivreur")
    int idlivreur;
    @SerializedName("loginlivreur")
    String loginlivreur;
    @SerializedName("mdplivreur")
    String mdplivreur;
    @SerializedName("nomlivreur")
    String nomlivreur;
    @SerializedName("prenomlivreur")
    String prenomlivreur;
    @SerializedName("tellivreur")
    String tellivreur;

    public int getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(int idlivreur) {
        this.idlivreur = idlivreur;
    }

    public String getLoginlivreur() {
        return loginlivreur;
    }

    public void setLoginlivreur(String loginlivreur) {
        this.loginlivreur = loginlivreur;
    }

    public String getMdplivreur() {
        return mdplivreur;
    }

    public void setMdplivreur(String mdplivreur) {
        this.mdplivreur = mdplivreur;
    }

    public String getNomlivreur() {
        return nomlivreur;
    }

    public void setNomlivreur(String nomlivreur) {
        this.nomlivreur = nomlivreur;
    }

    public String getPrenomlivreur() {
        return prenomlivreur;
    }

    public void setPrenomlivreur(String prenomlivreur) {
        this.prenomlivreur = prenomlivreur;
    }

    public String getTellivreur() {
        return tellivreur;
    }

    public void setTellivreur(String tellivreur) {
        this.tellivreur = tellivreur;
    }
}
