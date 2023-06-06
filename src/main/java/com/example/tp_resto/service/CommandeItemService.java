package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;

public interface CommandeItemService {

  CommandeItem getById(int id);


    //On doit l'attacher a une Commande
    //void addCommandeItem(MenuItem menuItem, CommandeItem foodItem);

    boolean updateCommandeItemById(Integer id, CommandeItem foodItem);

    boolean updateCommandeItemByNameOnCommande(Commande commande, CommandeItem foodItem);

    boolean deleteCommandeItemById(Integer id);

  boolean deleteCommandeItemByNameOnCommande(Commande commande, CommandeItem foodItem);

  CommandeItem addCommandeItem(CommandeItem commandeItem);
}
