package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Cette interface définit les services disponibles pour les objets CommandeItem.
 */
public interface CommandeItemService {

    /**
     * Récupère tous les objets CommandeItem.
     *
     * @return une liste de tous les objets CommandeItem.
     */
    @Transactional
    List<CommandeItem> findAll();

    /**
     * Récupère un objet CommandeItem par son id.
     *
     * @param id  de l'objet CommandeItem à récupérer.
     * @return un Optional contenant l'objet CommandeItem si trouvé, sinon un Optional vide.
     */
    @Transactional
    Optional<CommandeItem> findCommandeItemById(int id);

    /**
     * Enregistre un objet CommandeItem dans la base de données.
     *
     * @param commandeItem l'objet CommandeItem à enregistrer.
     * @return l'objet CommandeItem qui a été enregistré.
     */
    @Transactional
    CommandeItem saveCommandeItem(CommandeItem commandeItem);

    /**
     * Supprime un objet CommandeItem de la base de données par son id.
     *
     * @param id de l'objet CommandeItem à supprimer.
     */
    @Transactional
    void deleteCommandeItemById(int id);

    /**
     * Récupère une liste d'objets CommandeItem associés à un objet Commande spécifique.
     *
     * @param commande l'objet Commande pour lequel récupérer les objets CommandeItem.
     * @return une liste d'objets CommandeItem associés à l'objet Commande spécifié.
     */
    @Transactional
    List<CommandeItem> findByCommande(Commande commande);

    /**
     * Supprime un objet CommandeItem d'une liste d'objets CommandeItem.
     *
     * @param commandeItem l'objet CommandeItem à supprimer de la liste.
     */
    void deleteItemFromOrderItems(CommandeItem commandeItem);
}
