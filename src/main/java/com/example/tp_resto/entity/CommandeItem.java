package com.example.tp_resto.entity;

import jakarta.persistence.*;

@Entity
public class CommandeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "MenuItem")
    private MenuItem menuItem;
    private int quantity;

    public CommandeItem() {
    }

    public CommandeItem(int id, Commande commande, MenuItem menuItem, int quantity) {
        this.id = id;
        this.commande = commande;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return "CommandeItem{" +
                "id=" + id +
                ", commande=" + commande +
                ", menuItem=" + menuItem +
                ", quantity=" + quantity +
                '}';
    }
}
