package entities;

import java.util.Date;

/**
 * Created by Hajar on 18/03/2018.
 */

public class Commande {
    int idcommande;
    int idclient;
    int idlivreur;
    String State;
    String nom_livraison;
    String prenom_livraison;
    String tel_livraison;
    String adresse_livraison;
    Date datecommande;

    public int getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(int idlivreur) {
        this.idlivreur = idlivreur;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public int getIdclient() {
        return idclient;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public String getNom_livraison() {
        return nom_livraison;
    }

    public void setNom_livraison(String nom_livraison) {
        this.nom_livraison = nom_livraison;
    }

    public String getPrenom_livraison() {
        return prenom_livraison;
    }

    public void setPrenom_livraison(String prenom_livraison) {
        this.prenom_livraison = prenom_livraison;
    }

    public String getTel_livraison() {
        return tel_livraison;
    }

    public void setTel_livraison(String tel_livraison) {
        this.tel_livraison = tel_livraison;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }
}
