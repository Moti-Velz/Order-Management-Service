package com.example.tp_resto.service;

import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.exception.MenuItemNotFoundException;
import com.example.tp_resto.repository.IMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService{

    IMenuItem menuRepository;


    @Autowired
    MenuItemServiceImpl(IMenuItem menuRepository){
        this.menuRepository=menuRepository;
    }

    @Override
    public MenuItem getById(int id) {
        Optional<MenuItem> itemOpt = menuRepository.findById(id);
        MenuItem item = null;
        if(itemOpt.isPresent()) {
            item = itemOpt.get();
        } else {
            throw new MenuItemNotFoundException("On s'en fou de ce texte");
        }
        return item;
    }

    @Override
    public MenuItem getByName(String name) {
        return menuRepository.findByNameIgnoreCase(name);
    }


    @Override
    public List<MenuItem> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItemById(int id, MenuItem menuItem) {
        Optional<MenuItem> optionalMenuItem = menuRepository.findById(id);

        if(optionalMenuItem.isPresent()){
            MenuItem existingItem = optionalMenuItem.get();

            existingItem.setName(menuItem.getName());
            existingItem.setPrice(menuItem.getPrice());
            existingItem.setDescription((menuItem.getDescription()));

            return menuRepository.save(existingItem);
        } else {
            throw new MenuItemNotFoundException("MenuItem Not Found");
        }
    }

    @Override
    public boolean deleteMenuItemById(int id) {
        if(menuRepository.existsById(id)){
            menuRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<MenuItem> getAll() {
        return menuRepository.findAll();
    }
}
