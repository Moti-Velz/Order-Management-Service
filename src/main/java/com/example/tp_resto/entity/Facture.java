package com.example.tp_resto.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "commande_id"))
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "commande_id")
    private Commande commande;
    private boolean status;
    private Instant billTime;

    public Facture() {
    }

    public Facture(int id, Commande commande, double amount, boolean status, Instant billTime) {
        this.id = id;
        this.commande = commande;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
                ", status='" + status + '\'' +
                ", billTime=" + billTime +
                '}';
    }

    public double getPrixTotal(){
        double total = 0;
        Commande commande = this.getCommande();
        for(CommandeItem food : commande.getOrderItems()){
            total += food.getMenuItem().getPrice();
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return id == facture.id && Objects.equals(billTime, facture.billTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commande, status, billTime);
    }
}
