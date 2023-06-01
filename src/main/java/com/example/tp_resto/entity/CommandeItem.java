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

    @OneToOne
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
