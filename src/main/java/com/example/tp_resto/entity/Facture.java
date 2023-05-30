package com.example.tp_resto.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
    private double amount;
    private String status;
    private Date billTime;
}
