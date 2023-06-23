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

/**
 * Implémentation du service Commande.
 * Cette classe offre les services CRUD pour les objets Commande.
 */
@Service
public class CommandeServiceImpl implements CommandeService {

    private ICommandeRepo commandeRepository;

    private IFactureRepo factureRepository;

    /**
     * Constructeur de la classe CommandeServiceImpl.
     *
     * @param commandeRepository le dépôt pour les objets Commande.
     * @param factureRepository le dépôt pour les objets Facture.
     */
    @Autowired
    public CommandeServiceImpl(ICommandeRepo commandeRepository, IFactureRepo factureRepository) {
        this.commandeRepository = commandeRepository;

        this.factureRepository = factureRepository;
    }

    /**
     * Enregistre une nouvelle Commande.
     *
     * @param order la Commande à enregistrer.
     * @return la Commande enregistrée.
     */
    @Override
    public Commande saveCommande(Commande order) {
        return commandeRepository.save(order);
    }

    /**
     * Récupère une Commande en fonction de son id.
     *
     * @param id de la Commande à récupérer.
     * @return un Optional contenant la Commande si elle est trouvée, sinon un Optional vide.
     */
    @Override
    public Optional<Commande> getById(int id) {
        Optional<Commande> commandeOptional = commandeRepository.findById(id);
        if(!commandeOptional.isPresent()) {
            throw new RuntimeException("Commande No " + id + " introuvable");
        }
        return commandeOptional;
    }

    /**
     * Récupère toutes les Commandes.
     *
     * @return une liste de toutes les Commandes.
     */
    @Override
    public List<Commande> getAll() {
        return commandeRepository.findAll();
    }


    /**
     * Met à jour une Commande en fonction de son id.
     *
     * @param id de la Commande à mettre à jour.
     * @param newCommande la nouvelle Commande à enregistrer.
     * @return la Commande mise à jour.
     */
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

    /**
     * Supprime une Commande en fonction de son id.
     *
     * @param id de la Commande à supprimer.
     * @return true si la suppression a été effectuée avec succès, sinon false.
     */
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

    /**
     * Fonction utilitaire pour vérifier si un élément de Commande existe dans une Commande donnée.
     *
     * @param commandeItem l'élément de la Commande à vérifier.
     * @param commande la Commande dans laquelle vérifier.
     * @return l'ID de l'élément de la Commande s'il existe dans la Commande, sinon 0.
     */
    public int checkCommandeItem(CommandeItem commandeItem, Commande commande){
        for(CommandeItem commandeItem1 : commande.getOrderItems()) {
            if(commandeItem1.getMenuItem().getId() == commandeItem.getMenuItem().getId()){
                return commandeItem1.getId();
            }
        } return 0;
    }
}


