package com.example.tp_resto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int tableId;
    private String status;
    private Date orderTime;

    public Commande() {}

    public Commande(Integer id, int tableId, String status, Date orderTime) {
        this.id = id;
        this.tableId = tableId;
        this.status = status;
        this.orderTime = orderTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", tableId=" + tableId +
                ", status='" + status + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}