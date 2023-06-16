package com.example.tp_resto.service;

import com.example.tp_resto.entity.Facture;

import java.time.LocalDateTime;
import java.util.List;


public interface FactureService {

    Facture saveFacture(Facture facture);

    List<Facture> findByDate(LocalDateTime date);

    Facture updateFacture(int id, Facture facture);

    Facture findById(int id);

    List<Facture> findAll();

    boolean deleteFacture(Facture facture);

    boolean deleteFactureById(int id);
}
