package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.Facture;
import com.example.tp_resto.repository.ICommandeRepo;
import com.example.tp_resto.repository.IFactureRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private ICommandeRepo commandeRepository;
    //private CommandeItemService commandeItemService;
    private IFactureRepo factureRepository;

    @Autowired
    public CommandeServiceImpl(ICommandeRepo commandeRepository, IFactureRepo factureRepository) { //CommandeItemService commandeItemService,
        this.commandeRepository = commandeRepository;
        //this.commandeItemService = commandeItemService;
        this.factureRepository = factureRepository;
    }

    @Override
    public Commande saveCommande(Commande order) {
        return commandeRepository.save(order);
    }

    //done pour linstant
    @Override
    public Optional<Commande> getById(int id) {
        Optional<Commande> commandeOptional = commandeRepository.findById(id);
        if(!commandeOptional.isPresent()) {
            throw new RuntimeException("Commande No " + id + " introuvable");
        }
        return commandeOptional;
    }
    @Override
    public List<Commande> getAll() {
        return commandeRepository.findAll();
    }



    @Transactional
    @Override
    public Commande updateCommandeById(Integer id, Commande newCommande) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        Optional<Facture> optionalFacture = factureRepository.findFactureByCommande_Id(id);

        if (optionalCommande.isPresent()) {
            Commande existingCommande = optionalCommande.get();
            optionalFacture.ifPresent(existingCommande::setFactureBidirection);

            existingCommande.setOrderTime(newCommande.getOrderTime());
            existingCommande.getOrderItems().clear();
            existingCommande.setOrderTime(newCommande.getOrderTime());

            for(CommandeItem item : newCommande.getOrderItems()) {
                existingCommande.addItem(item);
            }
            return commandeRepository.save(existingCommande);
        } else {
            throw new RuntimeException("Commande id " + id + " introuvable");
        }
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

    //Helper Function
    public int checkCommandeItem(CommandeItem commandeItem, Commande commande){
        for(CommandeItem commandeItem1 : commande.getOrderItems()) {
            if(commandeItem1.getMenuItem().getId() == commandeItem.getMenuItem().getId()){
                return commandeItem1.getId();
            }
        } return 0;
    }

//    public Commande updateCommandeItemsByCommandeId(int i, List<CommandeItem> newOrderItems) {
//        return null;
//    }
}


