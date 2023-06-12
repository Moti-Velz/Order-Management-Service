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

    @Override
    public Commande getById(int id) {
        return commandeRepository.getById(id);
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

        return false;
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
            concreteCommandeItem.setQuantity(actualQte + commandeItem.getQuantity());
            commandeItemService.saveItem(concreteCommandeItem);
        } else {
            commandeItemService.saveItem(commandeItem);
        }
    }
}


