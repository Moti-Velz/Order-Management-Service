package com.example.tp_resto.service;

import com.example.tp_resto.entity.CommandeItem;

import java.util.Optional;

public interface CommandeItemService {

    CommandeItem saveItem(CommandeItem item);

    void updateCommandeItemById(Integer id, CommandeItem foodItem);

    void deleteCommandeItemById(Integer id);

    Optional<CommandeItem> getById(int id);

    //GetAllCommandeItemByCommandeId
}
