package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;

import java.util.List;

public interface CommandeService {
    Commande getById(int id);

    Commande createCommande(Commande commande);

    Commande updateCommandeById(Integer id, Commande orderItem);

    boolean deleteCommandeById(Integer id);

    List<Commande> findAll();

}
