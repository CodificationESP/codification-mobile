package com.codification.codificationesp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arame on 23/01/2018.
 */

public class Etage {
    private int numEtage;
    private int idEtage;
    private String batimentId;
    private List<Couloir> couloirs;

    public int getNumEtage() {
        return numEtage;
    }

    public void setNumEtage(int numEtage) {
        this.numEtage = numEtage;
    }

    public int getIdEtage() {
        return idEtage;
    }

    public void setIdEtage(int idEtage) {
        this.idEtage = idEtage;
    }

    public String getBatimentId() {
        return batimentId;
    }

    public void setBatimentId(String batimentId) {
        this.batimentId = batimentId;
    }

    public List<Couloir> getCouloirs() {
        return couloirs;
    }

    public void setCouloirs(List<Couloir> couloirs) {
        this.couloirs = couloirs;
    }

    public List<Chambre> getListechambres() {
        List<Chambre> chambres = new ArrayList();

        for (int i=0;i<couloirs.size();i++){
            Couloir c = couloirs.get(i);
            List<Chambre> chambrescouloir = c.getChambres();
            for (int j=0;j<chambrescouloir.size();j++) {
                    chambres.add(chambrescouloir.get(j));
            }
        }
        return chambres;
    }

}
