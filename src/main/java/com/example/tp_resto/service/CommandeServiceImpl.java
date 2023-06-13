package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.repository.ICommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private ICommandeRepo commandeRepository;
    private CommandeItemService commandeItemService;

    @Autowired
    public CommandeServiceImpl(ICommandeRepo commandeRepository, CommandeItemService commandeItemService) {
        this.commandeRepository = commandeRepository;
        this.commandeItemService = commandeItemService;
    }

    //done pour linstant
    @Override
    public Commande getById(int id) {
        Optional<Commande> commandeOptional = commandeRepository.findById(id);
        Commande commande = null;
        if(commandeOptional.isPresent())
        {
            commande = commandeOptional.get();
        }else{
            throw new RuntimeException("Commande id "+ id + " introuvable");
        }
        return commande;
    }


    @Override
    public List<Commande> getAll() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande saveCommande(Commande order) {
        return commandeRepository.save(order);
    }

    @Override
    public Commande updateCommandeById(Integer id, Commande orderItem) {

        return null;
    }

    @Override
    public boolean deleteCommandeById(Integer id) {
        Optional<Commande> commande = commandeRepository.findById(id);
        if(commande.isPresent()){
            commandeRepository.deleteById(id);
            return true;
        }else{
            throw new RuntimeException("Commande "+ id+" introuvable");

        }

    }

    public int checkCommandeItem(CommandeItem commandeItem, Commande commande){
        for(CommandeItem commandeItem1 : commande.getOrderItems()) {
            if(commandeItem1.getMenuItem().getId() == commandeItem.getMenuItem().getId()){
                return commandeItem1.getId();
            }
        }
        return 0;
    }

    @Override
    public void addItemToCommande(Commande commande, CommandeItem commandeItem) {

        int optionalCommandeItemId = checkCommandeItem(commandeItem, commande);
        if(optionalCommandeItemId != 0) {
            Optional<CommandeItem> existingCommandeItem = commandeItemService.getById(optionalCommandeItemId);
            int actualQte = existingCommandeItem.get().getQuantity();
            CommandeItem concreteCommandeItem = existingCommandeItem.get();
            concreteCommandeItem.setCommande(commande);
            concreteCommandeItem.setQuantity(actualQte + commandeItem.getQuantity());
            commandeItemService.saveItem(concreteCommandeItem);
        } else {
            commandeItemService.saveItem(commandeItem);
        }
    }
}


