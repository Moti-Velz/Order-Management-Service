package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;

import java.util.List;

public interface MenuItemService {

    MenuItem getById(int id);

    List<MenuItem> findAll();

    MenuItem createMenuItem(MenuItem menuItem);

    MenuItem updateMenuItemById(int id, MenuItem menuItem);

    //UpdateByName?

    boolean deleteMenuItemById(int id);
}
