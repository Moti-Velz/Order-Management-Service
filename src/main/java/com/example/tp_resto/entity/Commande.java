package com.example.tp_resto.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * id, orderItems, ordertime
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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
    private List<CommandeItem> orderItems = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private Facture facture;

    public Facture getFacture() {
        return facture;
    }
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime orderTime;

    public Commande() {
        orderTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public Commande(LocalDateTime orderTime) {
        this.orderTime = orderTime.truncatedTo(ChronoUnit.SECONDS);
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public void addItem(CommandeItem item) {

        if(orderItems == null) {
            orderItems = new ArrayList<>();
        }
        //verifie si le menuItem est la (avec son id)
        //Si oui -> incremente la qte
        //Si non -> ajouter normal

        orderItems.add(item);
        item.setCommande(this);
    }

    public void removeFacture() {
        facture.setCommande(null);
        this.facture = null;
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
        return orderItems;
    }

    public void setOrderItems(List<CommandeItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getPrixTotal(){
        double total = 0;
        for(CommandeItem food : orderItems){
            total += food.getMenuItem().getPrice();
        }
        return total;
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