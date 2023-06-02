package com.example.tp_resto.service;

import com.example.tp_resto.entity.MenuItem;
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
        return menuRepository.getById(id);
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItemById(int id, MenuItem menuItem) {
        Optional<MenuItem> optionalMenuItem = menuRepository.findById(id);

        if(optionalMenuItem.isPresent()){
            MenuItem existingItem = optionalMenuItem.get();

            // Assuming MenuItem has setName(), setPrice()... etc methods
            existingItem.setName(menuItem.getName());
            existingItem.setPrice(menuItem.getPrice());
            existingItem.setDescription((menuItem.getDescription()));
            // update any other fields as needed

            return menuRepository.save(existingItem);
        } else {
            return null;
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
