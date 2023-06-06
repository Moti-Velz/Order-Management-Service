package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.repository.ICommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
