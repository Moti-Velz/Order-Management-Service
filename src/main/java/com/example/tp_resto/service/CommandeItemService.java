package com.example.tp_resto.service;

import com.example.tp_resto.entity.CommandeItem;

public interface CommandeItemService {

    CommandeItem getById(int id);

    void addCommandeItem(CommandeItem foodItem);

    void updateCommandeItemById(Integer id, CommandeItem foodItem);

    void deleteCommandeItemById(Integer id);
}
