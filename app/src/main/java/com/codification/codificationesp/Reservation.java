package com.codification.codificationesp;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by Arame on 28/01/2018.
 */

public class Reservation {
  private transient int idReservation;
  private Date date;
  private String etat;
  private String etudiantId;
  private int chambreId;
  private Chambre chambre;

  public Chambre getChambre() {
    return chambre;
  }

  public void setChambre(Chambre chambre) {
    this.chambre = chambre;
  }

  public int getIdReservation() {
    return idReservation;
  }

  public void setIdReservation(int idReservation) {
    this.idReservation = idReservation;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getEtat() {
    return etat;
  }

  public void setEtat(String etat) {
    this.etat = etat;
  }

  public int getChambreId() {
    return chambreId;
  }

  public void setChambreId(int chambreId) {
    this.chambreId = chambreId;
  }

  public Reservation(Date date, String etat, String etudiantId, int chambreId) {
    this.date = date;
    this.etat = etat;
    this.etudiantId = etudiantId;
    this.chambreId = chambreId;
  }
}
