package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;

import java.util.List;
import java.util.Optional;

/**
 * Interface de service pour gérer les objets Commande.
 */
public interface CommandeService {

    /**
     * Récupère une Commande en fonction de son id.
     *
     * @param id  de la Commande à récupérer.
     * @return un Optional contenant la Commande si elle est trouvée, sinon un Optional vide.
     */
    Optional<Commande> getById(int id);

    /**
     * Récupère toutes les Commandes.
     *
     * @return une liste de toutes les Commandes.
     */
    List<Commande> getAll();

    /**
     * Enregistre une nouvelle Commande.
     *
     * @param order la Commande à enregistrer.
     * @return la Commande enregistrée.
     */
    Commande saveCommande(Commande order);

    /**
     * Met à jour une Commande en fonction de son id.
     *
     * @param id de la Commande à mettre à jour.
     * @param orderItem la nouvelle Commande à enregistrer.
     * @return la Commande mise à jour.
     */
    Commande updateCommandeById(Integer id, Commande orderItem);

    /**
     * Supprime une Commande en fonction de son id.
     *
     * @param id  de la Commande à supprimer.
     * @return true si la suppression a été effectuée avec succès, sinon false.
     */
    boolean deleteCommandeById(Integer id);
}
