package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.repository.ICommandeItemRepo;
import com.example.tp_resto.repository.ICommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeItemServiceImpl implements CommandeItemService{

    private ICommandeItemRepo commandeItemRepository;
    private ICommandeRepo commandeRepo;

    @Autowired
    public CommandeItemServiceImpl(ICommandeItemRepo commandeItemRepository,ICommandeRepo commandeRepo){
        this.commandeItemRepository = commandeItemRepository;
        this.commandeRepo = commandeRepo;
    }

//    @Override
//    public CommandeItem getById(int id) {
//        return null;
//    }

    @Override
    public CommandeItem getById(int id) {
        return null;
    }

//    @Override
//    public void addCommandeItem(MenuItem menuItem, CommandeItem foodItem) {
//
//    }

    @Override
    public boolean updateCommandeItemById(Integer id, CommandeItem foodItem) {
        return false;
    }

    @Override
    public boolean updateCommandeItemByNameOnCommande(Commande commande, CommandeItem foodItem) {
        return false;
    }


    @Override
    public boolean deleteCommandeItemById(Integer id) {

        return false;
    }

    @Override
    public boolean deleteCommandeItemByNameOnCommande(Commande commande, CommandeItem foodItem) {
        return false;
    }

    @Override
    public CommandeItem addCommandeItem(CommandeItem commandeItem) {
        return null;
    }
}
