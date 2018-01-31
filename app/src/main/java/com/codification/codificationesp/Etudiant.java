package com.codification.codificationesp;

/**
 * Created by Arame on 22/01/2018.
 */

public class Etudiant {
    private String numEtudiant;
    private String prenomEtudiant;
    private String nomEtudiant;
    private String dateNaissance;
    private String login;
    private String motdepasse;
    private String formation;
    private String genre;
    private String niveau;
    private String departement;
    private String email;
    private int utilisateurId;

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }



    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    private Reservation reservation;

    public Etudiant(String numetudiant, String prenometudiant, String nometudiant, String ddn, String login, String motdepasse, String formation, String genre, String niveau, String departement) {
        this.numEtudiant = numetudiant;
        this.prenomEtudiant = prenometudiant;
        this.nomEtudiant = nometudiant;
        this.dateNaissance = ddn;
        this.login = login;
        this.motdepasse = motdepasse;
        this.formation = formation;
        this.genre = genre;
        this.niveau = niveau;
        this.departement = departement;
    }

    public Etudiant() {
    }

    public String getNumetudiant() {
        return numEtudiant;
    }

    public void setNumetudiant(String numetudiant) {
        this.numEtudiant = numetudiant;
    }

    public String getPrenometudiant() {
        return prenomEtudiant;
    }

    public void setPrenometudiant(String prenometudiant) {
        this.prenomEtudiant = prenometudiant;
    }

    public String getNometudiant() {
        return nomEtudiant;
    }

    public void setNometudiant(String nometudiant) {
        this.nomEtudiant = nometudiant;
    }

    public String getDdn() {
        return dateNaissance;
    }

    public void setDdn(String ddn) {
        this.dateNaissance = ddn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
