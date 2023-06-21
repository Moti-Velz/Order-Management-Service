package com.example.tp_resto.service;

import com.example.tp_resto.entity.MenuItem;

import java.util.List;

public interface MenuItemService {

    MenuItem getById(int id);

    MenuItem getByName(String name);

    List<MenuItem> findAll();

    MenuItem save(MenuItem menuItem);

    MenuItem updateMenuItemById(int id, MenuItem menuItem);

    boolean deleteMenuItemById(int id);
}
