package com.example.tp_resto.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

/**
 * Cette classe représente un élément de commande.
 */

/**
 * id, commande, menuItem, quantity
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonPropertyOrder({ "id", "menuItem", "quantity", "commande" })
@Entity
public class CommandeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="commande_id_FK") //On specifie le nom de la colonne
    private Commande commande;
    @ManyToOne
    private MenuItem menuItem;
   
    private int quantity;

    /**
     * Constructeur par défaut de la classe CommandeItem.
     */
    public CommandeItem() {
    }

    /**
     * Constructeur de la classe CommandeItem avec la quantité spécifiée.
     *
     * @param quantity La quantité de l'élément de commande.
     */
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

    /**
     * Récupère la commande associée à l'élément de commande.
     *
     * @return La commande associée à l'élément de commande.
     */
    public Commande getCommande() {
        return commande;
    }

    /**
     * Définit la commande associée à l'élément de commande.
     *
     * @param commande La commande associée à l'élément de commande.
     */
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    /**
     * Récupère le menu item associé à l'élément de commande.
     *
     * @return Le menu item associé à l'élément de commande.
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Définit le menu item associé à l'élément de commande.
     *
     * @param menuItem Le menu item associé à l'élément de commande.
     */
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
