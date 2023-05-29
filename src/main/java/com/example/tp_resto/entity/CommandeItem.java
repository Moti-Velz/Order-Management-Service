package com.example.tp_resto.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CommandeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int orderId;
    private int menuItemId;
    private int quantity;

    public CommandeItem() {
    }

    public CommandeItem(int id, int orderId, int menuItemId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
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
                ", orderId=" + orderId +
                ", menuItemId=" + menuItemId +
                ", quantity=" + quantity +
                '}';
    }
}
