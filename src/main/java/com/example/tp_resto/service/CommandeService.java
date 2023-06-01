package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;

public interface CommandeService {
    Commande getById(int id);

    void addCommande(CommandeItem orderItem);

    void updateCommandeById(Integer id, Commande orderItem);

    void deleteCommandeById(Integer id);
}
