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

    Commande updateCommandeItemsByCommandeId(Integer id, List<CommandeItem> newOrderItems);

    boolean deleteCommandeById(Integer id);

    void addItemToCommande(Commande byId, CommandeItem commandeItem);
}
