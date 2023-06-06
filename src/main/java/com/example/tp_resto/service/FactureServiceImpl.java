package com.example.tp_resto.service;

import com.example.tp_resto.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FactureServiceImpl implements FactureService{
    @Override
    public List<MenuItem> getAlMenuItem() {
        return null;
    }

    @Override
    public boolean deleteMenuItem(String menuItemName) {
        return false;
    }
}
