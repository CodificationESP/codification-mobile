package com.codification.codificationesp;

import java.util.List;

/**
 * Created by Arame on 23/01/2018.
 */

public class Couloir {
    private String nomCouloir;
    private int idCouloir;
    private int etageId;
    private List<Chambre> chambres;

    public int getIdCouloir() {
        return idCouloir;
    }

    public void setIdCouloir(int idCouloir) {
        this.idCouloir = idCouloir;
    }

    public int getEtageId() {
        return etageId;
    }

    public void setEtageId(int etageId) {
        this.etageId = etageId;
    }

    public String getNomCouloir() {
        return nomCouloir;
    }

    public void setNomCouloir(String nomCouloir) {
        this.nomCouloir = nomCouloir;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

}
