package com.example.tp_resto.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int restaurantId;
    private String name;
    private double price;

    public MenuItem() {
    }

    public MenuItem(int id, int restaurantId, String name, double price) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
