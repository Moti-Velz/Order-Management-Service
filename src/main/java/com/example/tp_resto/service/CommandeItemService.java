package com.example.tp_resto.service;

import com.example.tp_resto.entity.CommandeItem;

public interface CommandeItemService {

    void saveItem(CommandeItem item);

    void updateCommandeItemById(Integer id, CommandeItem foodItem);

    void deleteCommandeItemById(Integer id);
}
