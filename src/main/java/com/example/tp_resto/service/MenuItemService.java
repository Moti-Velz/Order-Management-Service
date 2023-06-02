package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;

public interface MenuItemService {

    MenuItem getById(int id);

    MenuItem createMenuItem(MenuItem menuItem);

    MenuItem updateMenuItemById(int id, MenuItem menuItem);

    boolean deleteMenuItemById(int id);
}
