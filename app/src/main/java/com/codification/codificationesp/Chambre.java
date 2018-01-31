package com.codification.codificationesp;

/**
 * Created by Arame on 20/01/2018.
 */

public class Chambre {
    private int idChambre;
    private String nomChambre;
    private int couloirId;

    public Chambre(int id, String numchambre,int idcouloir) {
        this.nomChambre = numchambre;
        this.idChambre = id;
        this.couloirId = idcouloir;

    }

    public int getCouloirId() {
        return couloirId;
    }

    public void setCouloirId(int couloirId) {
        this.couloirId = couloirId;
    }

    public String getNomchambre() {
        return nomChambre;
    }

    public void setNomchambre(String numchambre) {
        this.nomChambre = numchambre;
    }
    public int getId() {
        return idChambre;
    }

    public void setId(int id) {
        this.idChambre = id;
    }

}
