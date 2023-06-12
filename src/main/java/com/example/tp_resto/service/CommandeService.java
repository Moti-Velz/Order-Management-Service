package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;

import java.util.List;

public interface CommandeService {
    Commande getById(int id);

    List<Commande> getAll();

    Commande saveCommande(Commande order);

    Commande updateCommandeById(Integer id, Commande orderItem);

    boolean deleteCommandeById(Integer id);

    void addItemToCommande(Commande byId, CommandeItem commandeItem);
}
