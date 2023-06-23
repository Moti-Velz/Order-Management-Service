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


/**
 * Implémentation de l'interface CommandeItemService.
 * Cette classe offre les services CRUD pour les objets CommandeItem.
 */
@Service
public class CommandeItemServiceImpl implements CommandeItemService{

    private ICommandeItemRepo commandeItemRepository;
    private ICommandeRepo commandeRepository;

    /**
     * Constructeur de la classe CommandeItemServiceImpl.
     *
     * @param commandeItemRepository le dépôt pour les objets CommandeItem.
     * @param commandeRepository le dépôt pour les objets Commande.
     */
    @Autowired
    public CommandeItemServiceImpl(ICommandeItemRepo commandeItemRepository, ICommandeRepo commandeRepository){
        this.commandeItemRepository = commandeItemRepository;
        this.commandeRepository = commandeRepository;
    }

    /**
     * Récupère tous les éléments de la commande.
     *
     * @return une liste de tous les éléments de la commande.
     */
    @Override
    @Transactional
    public List<CommandeItem> findAll() {
        return commandeItemRepository.findAll();
    }

    /**
     * Récupère un élément de la commande par son id.
     *
     * @param id de l'élément de la commande à récupérer.
     * @return un Optional contenant l'élément de la commande si il est trouvé, sinon un Optional vide.
     * @throws RuntimeException si l'élément de la commande n'est pas trouvé.
     */
    @Override
    @Transactional
    public Optional<CommandeItem> findCommandeItemById(int id) {
        Optional<CommandeItem> commandeItemOptional = commandeItemRepository.findById(id);
        if(commandeItemOptional.isPresent())
        {
            return commandeItemOptional;
        }else{
            throw new RuntimeException("Commande item id "+ id +" introuvable");
        }
    }

    /**
     * Sauvegarde un élément de la commande dans la base de données.
     *
     * @param commandeItem l'élément de la commande à sauvegarder.
     * @return l'élément de la commande sauvegardé.
     */
    @Override
    @Transactional
    public CommandeItem saveCommandeItem(CommandeItem commandeItem) {
        return commandeItemRepository.save(commandeItem);
    }

    /**
     * Supprime un élément de la commande de la base de données par son id.
     *
     * @param id de l'élément de la commande à supprimer.
     */
    @Override
    @Transactional
    public void deleteCommandeItemById(int id) {
        commandeItemRepository.deleteById(id);
    }

    /**
     * Récupère tous les éléments de la commande pour une commande spécifique.
     *
     * @param commande la commande pour laquelle récupérer les éléments de la commande.
     * @return une liste des éléments de la commande pour la commande spécifiée.
     */
    @Override
    @Transactional
    public List<CommandeItem> findByCommande(Commande commande) {
        return commandeItemRepository.findByCommande(commande);
    }

    /**
     * Supprime un élément de la commande de la liste des éléments de commande.
     *
     * @param commandeItem l'élément de la commande à supprimer.
     * @throws RuntimeException si l'élément de la commande est null ou si la commande associée est introuvable.
     */
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
