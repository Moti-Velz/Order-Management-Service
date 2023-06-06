package com.example.tp_resto.service;

import com.example.tp_resto.entity.MenuItem;

import java.util.List;

public interface FactureService {

    List<MenuItem> getAlMenuItem();

    boolean deleteMenuItem(String menuItemName);
}
