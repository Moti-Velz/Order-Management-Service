package com.example.tp_resto.service;

import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;

import java.util.List;

import java.util.Optional;

public interface CommandeItemService {

    CommandeItem saveItem(CommandeItem item);

    List<CommandeItem> findAll();

    void updateCommandeItemById(Integer id, CommandeItem foodItem);

    void deleteCommandeItemById(Integer id);

    Optional<CommandeItem> getById(int id);

    //GetAllCommandeItemByCommandeId
}
