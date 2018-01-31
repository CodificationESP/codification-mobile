package com.codification.codificationesp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arame on 20/01/2018.
 */
//
public class Batiment {
    private String nomBatiment;
    private List<Etage> etages;

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    private List<Chambre> chambres;

//    public Batiment(String nombatiment, List<Etage> etages) {
//        this.nombatiment = nombatiment;
//        this.etages = etages;
//    }
    public Batiment(String nombatiment, List<Chambre> chambres) {
        this.nomBatiment = nombatiment;
        this.chambres = chambres;
    }

    public String getNombatiment() {
        return nomBatiment;
    }
    public void setNombatiment(String nombatiment) {
        this.nomBatiment = nombatiment;
    }

    public List<Etage> getEtages() {
        return etages;
    }
    public void setEtages(List<Etage> etages) {
        this.etages = etages;
    }


    public List<Chambre> getChambresbatiment() {
        List<Chambre> chambres = new ArrayList();
        for (int k=0;k<etages.size();k++){
            List<Couloir> couloirs = etages.get(k).getCouloirs();
            for (int i=0;i<couloirs.size();i++){
                Couloir c = couloirs.get(i);
                List<Chambre> chambrescouloir = c.getChambres();
                for (int j=0;j<chambrescouloir.size();j++) {
                    chambres.add(chambrescouloir.get(j));
                }
            }

        }
        return chambres;

    }

}
