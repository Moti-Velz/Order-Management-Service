package com.example.tp_resto.service;
import com.example.tp_resto.entity.Facture;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Interface pour le service de Facture.
 * Cette interface définit les méthodes CRUD pour les objets Facture.
 */
public interface FactureService {

    /**
     * Enregistre une nouvelle Facture.
     *
     * @param facture la Facture à enregistrer.
     * @return la Facture enregistrée.
     */
    Facture saveFacture(Facture facture);

    /**
     * Récupère une liste de Factures en fonction de leur date.
     *
     * @param date la date des Factures à récupérer.
     * @return une liste de Factures correspondant à la date donnée.
     */
    List<Facture> findByDate(LocalDateTime date);

    /**
     * Met à jour une Facture en fonction de son id.
     *
     * @param id l'identifiant de la Facture à mettre à jour.
     * @param facture la nouvelle Facture à enregistrer.
     * @return la Facture mise à jour.
     */
    Facture updateFacture(int id, Facture facture);

    /**
     * Récupère une Facture en fonction de son id.
     *
     * @param id l'identifiant de la Facture à récupérer.
     * @return la Facture correspondante à l'identifiant donné.
     */
    Facture findById(int id);

    /**
     * Récupère toutes les Factures.
     *
     * @return une liste de toutes les Factures.
     */
    List<Facture> findAll();
    /**
     * Supprime une Facture en fonction de son id.
     *
     * @param id de la Facture à supprimer.
     * @return true si la suppression a été effectuée avec succès, sinon false.
     */
    boolean deleteFactureById(int id);

    /**
     * Crée une nouvelle Facture pour une Commande existante en fonction de son id.
     *
     * @param id de la Commande pour laquelle créer une nouvelle Facture.
     * @return la nouvelle Facture créée pour la Commande existante.
     */
    Facture createFactureExistingCommande(int id);
}
