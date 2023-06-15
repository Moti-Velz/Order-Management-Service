package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.repository.ICommandeItemRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
public class CommandeItemServiceImpl implements CommandeItemService{

    private ICommandeItemRepo commandeItemRepository;

    @Autowired
    public CommandeItemServiceImpl(ICommandeItemRepo commandeItemRepository){
        this.commandeItemRepository = commandeItemRepository;
    }

    @Override
    @Transactional
    public List<CommandeItem> findAll() {
        return commandeItemRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<CommandeItem> findById(int theId) {
        return commandeItemRepository.findById(theId);
    }

    @Override
    @Transactional
    public CommandeItem save(CommandeItem theCommandeItem) {
        return commandeItemRepository.save(theCommandeItem);
    }


    @Override
    @Transactional
    public void deleteById(int theId) {
        commandeItemRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public List<CommandeItem> findByCommande(Commande theCommande) {
        return commandeItemRepository.findByCommande(theCommande);
    }
}
