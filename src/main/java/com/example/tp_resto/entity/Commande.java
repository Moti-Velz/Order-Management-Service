package com.example.tp_resto.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
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
    @JoinColumn(name="facture_id_FK")
    private Facture facture;

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    private Timestamp orderTime;

    public Commande() {
    }

    public Commande( Timestamp orderTime) {
        this.orderTime = orderTime;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
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