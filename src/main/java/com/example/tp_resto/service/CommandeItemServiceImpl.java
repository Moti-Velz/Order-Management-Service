package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.repository.ICommandeItemRepo;
import com.example.tp_resto.repository.ICommandeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
public class CommandeItemServiceImpl implements CommandeItemService{

    private ICommandeItemRepo commandeItemRepository;
    private ICommandeRepo commandeRepository;

    @Autowired
    public CommandeItemServiceImpl(ICommandeItemRepo commandeItemRepository, ICommandeRepo commandeRepository){
        this.commandeItemRepository = commandeItemRepository;
        this.commandeRepository = commandeRepository;
    }

    @Override
    @Transactional
    public List<CommandeItem> findAll() {
        return commandeItemRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<CommandeItem> findById(int theId) {
        Optional<CommandeItem> commandeItemOptional = commandeItemRepository.findById(theId);
        if(commandeItemOptional.isPresent())
        {
            return commandeItemOptional;
        }else{
            throw new RuntimeException("Commande item id "+ theId +" introuvable");
        }
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

    @Override
    public void deleteItemFromOrderItems(CommandeItem commandeItem) {
        if(commandeItem == null) {
            throw new RuntimeException("Item de Commande ne peut être nul.");
        }
        Optional<Commande> commandeOpt = commandeRepository.findById(commandeItem.getCommande().getId());

        if(!commandeOpt.isPresent()) {
            throw new RuntimeException("Commande Associée introuvable");
        }
        Commande commande = commandeOpt.get();
        commande.getOrderItems().remove(commandeItem);
        commandeRepository.save(commande);
        commandeItemRepository.delete(commandeItem);
    }
}
