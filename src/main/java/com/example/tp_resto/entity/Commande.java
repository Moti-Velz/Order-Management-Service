package com.example.tp_resto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numCommande;
    private String statut;
    private double prix;
    private String typePaiment;

    public Commande() {
        //ceci est un commentaire

    }

    public Commande(String numCommande, String statut, double prix, String typePaiment) {

        this.numCommande = numCommande;
        this.statut = statut;
        this.prix = prix;
        this.typePaiment = typePaiment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getStatut() { return statut; }

    public void setStatut(String statut) { this.statut = statut; }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getTypePaiment() {
        return typePaiment;
    }

    public void setTypePaiment(String typePaiment) {
        this.typePaiment = typePaiment;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", numCommande='" + numCommande + '\'' +
                ", statut=" + statut +
                ", prix=" + prix +
                ", typePaiment='" + typePaiment + '\'' +
                '}';
    }
}
