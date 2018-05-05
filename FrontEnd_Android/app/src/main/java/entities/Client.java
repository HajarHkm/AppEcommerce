package entities;

/**
 * Created by Hajar on 21/02/2018.
 */

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Client {
    @SerializedName("idclient")
    int idclient;
    @SerializedName("loginclient")
    String loginclient;
    @SerializedName("mdpclient")
    String mdpclient;
    @SerializedName("nomclient")
    String nomclient;
    @SerializedName("prenomclient")
    String prenomclient;
    @SerializedName("telclient")
    String telclient;
    @SerializedName("adressecli")
    String adressecli;
    @SerializedName("datenaissance")
    Date datenaissance;
    @SerializedName("email")
    String email;

    public int getIdclient() {
        return idclient;
    }

    public String getLoginclient() {
        return loginclient;
    }

    public String getMdpclient() {
        return mdpclient;
    }

    public String getNomclient() {
        return nomclient;
    }

    public String getPrenomclient() {
        return prenomclient;
    }

    public String getTelclient() {
        return telclient;
    }

    public String getAdressecli() {
        return adressecli;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public void setLoginclient(String loginclient) {
        this.loginclient = loginclient;
    }

    public void setMdpclient(String mdpclient) {
        this.mdpclient = mdpclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public void setPrenomclient(String prenomclient) {
        this.prenomclient = prenomclient;
    }

    public void setTelclient(String telclient) {
        this.telclient = telclient;
    }

    public void setAdressecli(String adressecli) {
        this.adressecli = adressecli;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
