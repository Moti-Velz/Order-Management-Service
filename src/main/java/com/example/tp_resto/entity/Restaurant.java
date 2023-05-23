package com.example.tp_resto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private String numeroTel;
    private double etoile;

    public Restaurant() {
    }

    public Restaurant(String name, String location, String numeroTel, double etoile) {
        this.name = name;
        this.location = location;
        this.numeroTel = numeroTel;
        this.etoile = etoile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public double getEtoile() {
        return etoile;
    }

    public void setEtoile(double etoile) {
        this.etoile = etoile;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", numeroTel='" + numeroTel + '\'' +
                ", etoile=" + etoile +
                '}';
    }
}
