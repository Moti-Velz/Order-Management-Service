package com.example.tp_resto.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int orderId;
    private double amount;
    private String status;
    private Date billTime;
}
