package com.example.tp_resto.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
    private double prix;
    private String status;
    private Instant billTime;

    public Facture() {
    }

    public Facture(int id, Commande commande, double amount, String status, Instant billTime) {
        this.id = id;
        this.commande = commande;
        this.prix = amount;
        this.status = status;
        this.billTime = billTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getBillTime() {
        return billTime;
    }

    public void setBillTime(Instant billTime) {
        this.billTime = billTime;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", commande=" + commande +
                ", prix=" + prix +
                ", status='" + status + '\'' +
                ", billTime=" + billTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return id == facture.id && Double.compare(facture.prix, prix) == 0 && Objects.equals(commande, facture.commande) && Objects.equals(status, facture.status) && Objects.equals(billTime, facture.billTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commande, prix, status, billTime);
    }
}
