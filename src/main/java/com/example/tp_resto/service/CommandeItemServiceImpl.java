package com.example.tp_resto.service;

import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.repository.ICommandeItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeItemServiceImpl implements CommandeItemService{

    private ICommandeItemRepo commandeItemRepository;

    @Autowired
    public CommandeItemServiceImpl(ICommandeItemRepo commandeItemRepository){
        this.commandeItemRepository = commandeItemRepository;
    }

    @Override
    public void saveItem(CommandeItem item) {
        commandeItemRepository.save(item);
    }

    @Override
    public void updateCommandeItemById(Integer id, CommandeItem foodItem) {

    }

    @Override
    public void deleteCommandeItemById(Integer id) {

    }
}
