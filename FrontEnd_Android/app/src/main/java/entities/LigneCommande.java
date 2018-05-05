package entities;

/**
 * Created by Hajar on 18/03/2018.
 */

public class LigneCommande {

    int idligne;
    int idcommande;
    int idproduit;
    int quantit;

    public int getIdligne() {
        return idligne;
    }

    public void setIdligne(int idligne) {
        this.idligne = idligne;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getQuantit() {
        return quantit;
    }

    public void setQuantit(int quantit) {
        this.quantit = quantit;
    }
}
