package com.codification.codificationesp;

import java.util.List;

/**
 * Created by Arame on 20/01/2018.
 */

public class Batiment {
    private String nombatiment;
    private List<Chambre> listechambres;

    public Batiment(String nombatiment, List<Chambre> listechambres) {
        this.nombatiment = nombatiment;
        this.listechambres = listechambres;
    }

    public String getNombatiment() {
        return nombatiment;
    }

    public void setNombatiment(String nombatiment) {
        this.nombatiment = nombatiment;
    }

    public List<Chambre> getListechambres() {
        return listechambres;
    }

    public void setListechambres(List<Chambre> listechambres) {
        this.listechambres = listechambres;
    }


}
