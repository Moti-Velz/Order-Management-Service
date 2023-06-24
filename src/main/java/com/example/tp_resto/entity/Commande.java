package com.example.tp_resto.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * id, orderItems, ordertime
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "facture_id"))
@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // MappedBy: Specifie le nom du champ de l'autre coté
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "commande",
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    //@JsonBackReference
    //@JoinColumn(name = "commande_id") quand on utilise mappedBy, on ne met pas cette annotation
    private List<CommandeItem> orderItems;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facture_id")
    private Facture facture;

    public Facture getFacture() {
        return facture;
    }
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime orderTime;

    /**
     * Constructeur par défaut de la classe Commande.
     * Initialise la date de commande à l'instant actuel.
     */
    public Commande() {
        orderTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Constructeur de la classe Commande avec la date de commande spécifiée.
     *
     * @param orderTime La date de commande.
     */
    public Commande(LocalDateTime orderTime) {
        this.orderTime = orderTime.truncatedTo(ChronoUnit.SECONDS);
    }


    /**
     * Supprime la facture associée à la commande.
     */
    public void removeFacture() {
        facture.setCommande(null);
        this.facture = null;
    }
    /**
     * Associe une facture à la commande de manière bidirectionnelle.
     *
     * @param facture La facture à associer.
     */
    public void setFactureBidirection(Facture facture) {
        this.facture = facture;
        facture.setCommande(this);
    }


    /**
     * Ajoute un élément de commande à la commande.
     * Si l'élément de commande existe déjà, incrémente la quantité.
     *
     * @param item L'élément de commande à ajouter.
     */
    @Transactional
    public void addItem(CommandeItem item) {
        if(orderItems == null) {
            orderItems = new ArrayList<>();
        }

        if(this.hasItem(item.getMenuItem())) {
            this.incrementItem(item, item.getQuantity());
        } else {
            this.orderItems.add(item);
            item.setCommande(this);

        }
    }
    /**
     * Vérifie si la commande contient un élément de menu spécifique.
     *
     * @param item L'élément de menu à rechercher.
     * @return true si l'élément de menu est présent dans la commande, sinon false.
     */
    public boolean hasItem(MenuItem item) {
        return this.orderItems.stream()
                .anyMatch(i -> i.getMenuItem().equals(item));
    }

    /**
     * Incrémente la quantité d'un élément de menu existant dans la commande.
     *
     * @param item     L'élément de menu à incrémenter.
     * @param quantity La quantité à ajouter.
     */
    public void incrementItem(CommandeItem item, int quantity) {
        this.orderItems.stream()
                .filter(i -> i.getMenuItem().equals(item.getMenuItem()))
                .findFirst()
                .ifPresent(i -> i.setQuantity(i.getQuantity() + quantity));
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CommandeItem> getOrderItems() {
        if(orderItems == null) {
            orderItems = new ArrayList<>();
        }
        return orderItems;
    }

    public void setOrderItems(List<CommandeItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                ", orderTime=" + orderTime +
                '}';
    }
}