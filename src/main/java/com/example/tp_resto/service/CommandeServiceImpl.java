package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.repository.ICommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private ICommandeRepo commandeRepository;

    @Autowired
    public CommandeServiceImpl(ICommandeRepo commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Commande getById(int id) {
        return commandeRepository.getById(id);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
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
        }

        return false;
    }

    @Override
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }



}
