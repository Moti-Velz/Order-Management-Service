package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.repository.ICommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeServiceImpl implements CommandeService {

    private ICommandeRepo commandeRepository;

    @Autowired
    public CommandeServiceImpl(ICommandeRepo commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Commande getById(int id) {
        return null;
    }

    @Override
    public void addCommande(CommandeItem orderItem) {

    }

    @Override
    public void updateCommandeById(Integer id, Commande orderItem) {

    }

    @Override
    public void deleteCommandeById(Integer id) {

    }
}
