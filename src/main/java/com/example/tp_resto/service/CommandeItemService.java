package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import jakarta.transaction.Transactional;

import java.util.List;

import java.util.Optional;

public interface CommandeItemService {

    @Transactional
    List<CommandeItem> findAll();

    @Transactional
    Optional<CommandeItem> findById(int theId);

    @Transactional
    CommandeItem save(CommandeItem theCommandeItem);

    @Transactional
    void deleteById(int theId);

    @Transactional
    List<CommandeItem> findByCommande(Commande theCommande);

}
