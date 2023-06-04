package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;

public interface CommandeService {
    Commande getById(int id);

    Commande createCommande(CommandeItem orderItem);

    Commande updateCommandeById(Integer id, Commande orderItem);

    boolean deleteCommandeById(Integer id);
}
