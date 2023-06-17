package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;

import java.util.List;
import java.util.Optional;

public interface CommandeService {
    Optional<Commande> getById(int id);

    List<Commande> getAll();

    Commande saveCommande(Commande order);

    Commande updateCommandeById(Integer id, Commande orderItem);

    boolean deleteCommandeById(Integer id);
}
