package com.example.tp_resto.service;

import com.example.tp_resto.entity.CommandeItem;

public interface CommandeItemService {

//    CommandeItem getById(int id);


    //On doit l'attacher a une Commande
    void addCommandeItem(CommandeItem foodItem);

    void saveItem(CommandeItem item);

    void updateCommandeItemById(Integer id, CommandeItem foodItem);

    void deleteCommandeItemById(Integer id);
}
