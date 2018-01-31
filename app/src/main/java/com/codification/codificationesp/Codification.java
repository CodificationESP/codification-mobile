package com.codification.codificationesp;

import java.util.Date;

/**
 * Created by Arame on 28/01/2018.
 */

public class Codification {
    private String idCodification;
    private String nomChambre;
    private Date date;
    private String etat;
    private String etudiantId;
    private int chambreId;

    public String getIdCodification() {
        return idCodification;
    }

    public void setIdCodification(String idCodification) {
        this.idCodification = idCodification;
    }

    public String getNomChambre() {
        return nomChambre;
    }

    public void setNomChambre(String nomChambre) {
        this.nomChambre = nomChambre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(String etudiantId) {
        this.etudiantId = etudiantId;
    }

    public int getChambreId() {
        return chambreId;
    }

    public void setChambreId(int chambreId) {
        this.chambreId = chambreId;
    }


    public Codification(String nomChambre, Date date, String etat, String etudiantId, int chambreId) {
        this.nomChambre = nomChambre;
        this.date = date;
        this.etat = etat;
        this.etudiantId = etudiantId;
        this.chambreId = chambreId;
    }
}
