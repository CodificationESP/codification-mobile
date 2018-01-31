package com.codification.codificationesp;

/**
 * Created by Arame on 27/01/2018.
 */

public class Contrainte {

    private String valeur;
    private String comparaison;
    private String attribut;

    public Contrainte(String attribut, String valeur, String comparaison) {
        this.attribut = attribut;
        this.valeur = valeur;
        this.comparaison = comparaison;
    }
    public String getAttribut() {
        return attribut;
    }

    public void setAttribut(String attribut) {
        this.attribut = attribut;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getComparaison() {
        return comparaison;
    }

    public void setComparaison(String comparaison) {
        this.comparaison = comparaison;
    }
}
