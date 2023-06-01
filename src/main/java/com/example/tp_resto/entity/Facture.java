package com.example.tp_resto.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
    private double amount;
    private String status;
    private Date billTime;

    public Facture() {
    }

    public Facture(int id, Commande commande, double amount, String status, Date billTime) {
        this.id = id;
        this.commande = commande;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", commande=" + commande +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", billTime=" + billTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return id == facture.id && Double.compare(facture.amount, amount) == 0 && Objects.equals(commande, facture.commande) && Objects.equals(status, facture.status) && Objects.equals(billTime, facture.billTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commande, amount, status, billTime);
    }
}
