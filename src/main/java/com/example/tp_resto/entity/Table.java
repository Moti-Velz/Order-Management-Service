package com.example.tp_resto.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int restaurantId;
    private int seats;

    public Table(int id, int restaurantId, int seats) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.seats = seats;
    }

    public Table() {
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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", seats=" + seats +
                '}';
    }


}