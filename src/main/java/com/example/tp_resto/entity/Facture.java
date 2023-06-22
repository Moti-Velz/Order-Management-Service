package com.example.tp_resto.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Cette classe représente une facture.
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonPropertyOrder({ "id", "status", "billTime", "prixTotal", "commande" })
@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Commande commande;
    private boolean status;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime billTime;

    /**
     * Constructeur par défaut de la classe Facture.
     * Initialise le temps de facturation à l'instant actuel.
     */
    public Facture() {
        billTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Constructeur de la classe Facture avec le temps de facturation spécifié.
     *
     * @param billTime Le temps de facturation.
     */

    public Facture(LocalDateTime billTime) {
        this.billTime = billTime.truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Constructeur de la classe Facture avec les paramètres spécifiés.
     *
     * @param id       L'ID de la facture.
     * @param commande La commande associée à la facture.
     * @param status   Le statut de la facture.
     * @param billTime Le temps de facturation.
     */
    public Facture(int id, Commande commande, boolean status, LocalDateTime billTime) {
        this.id = id;
        this.commande = commande;
        this.status = status;
        this.billTime = billTime;
        calculatePrixTotal();
    }

    @JsonGetter("prixTotal")
    private String calculatePrixTotal() {
        double total = 0;
        for(CommandeItem item : this.commande.getOrderItems()) {
            total += item.getQuantity() * item.getMenuItem().getPrice();
        }
        return String.format("%.2f", total);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Récupère la commande associée à la facture.
     *
     * @return La commande associée à la facture.
     */
    public Commande getCommande() {
        return commande;
    }

    /**
     * Définit la commande associée à la facture.
     *
     * @param commande La commande associée à la facture.
     */
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Récupère le temps de facturation.
     *
     * @return Le temps de facturation.
     */
    public LocalDateTime getBillTime() {
        return billTime;
    }

    /**
     * Définit le temps de facturation.
     *
     * @param billTime Le temps de facturation.
     */
    public void setBillTime(LocalDateTime billTime) {
        this.billTime = billTime != null ? billTime.truncatedTo(ChronoUnit.SECONDS) : null;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", commande=" + commande +
                ", status=" + status +
                ", billTime=" + billTime +
                '}';
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