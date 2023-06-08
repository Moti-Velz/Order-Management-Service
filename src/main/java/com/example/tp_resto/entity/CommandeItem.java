package com.example.tp_resto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

/**
 * id, commande, menuItem, quantity
 */
@Entity
public class CommandeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="commande_id_FK") //On specifie le nom de la colonne
    @JsonBackReference
    private Commande commande;
    @OneToOne
    private MenuItem menuItem;
    private int quantity;

    public CommandeItem() {
    }

    public CommandeItem(int quantity) {
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
        return "{" +
                "id=" + id +
                ", menuItem=" + menuItem +
                ", quantity=" + quantity +
                "}\n";
    }
}
