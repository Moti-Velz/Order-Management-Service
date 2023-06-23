package com.example.tp_resto.service;

import com.example.tp_resto.entity.MenuItem;

import java.util.List;

/**
 * Service pour les objets MenuItem.
 * Fournit les méthodes CRUD pour les objets MenuItem.
 */
public interface MenuItemService {

    /**
     * Récupère un MenuItem en fonction de son id.
     *
     * @param id du MenuItem à récupérer.
     * @return le MenuItem correspondant à l'identifiant donné.
     */
    MenuItem getById(int id);

    /**
     * Récupère un MenuItem en fonction de son nom.
     *
     * @param name le nom du MenuItem à récupérer.
     * @return le MenuItem correspondant au nom donné.
     */
    MenuItem getByName(String name);

    /**
     * Récupère tous les MenuItem.
     *
     * @return une liste de tous les MenuItem.
     */
    List<MenuItem> findAll();

    /**
     * Enregistre un nouveau MenuItem.
     *
     * @param menuItem le MenuItem à enregistrer.
     * @return le MenuItem enregistré.
     */
    MenuItem save(MenuItem menuItem);

    /**
     * Met à jour un MenuItem en fonction de son id.
     *
     * @param id du MenuItem à mettre à jour.
     * @param menuItem le nouveau MenuItem à enregistrer.
     * @return le MenuItem mis à jour.
     */
    MenuItem updateMenuItemById(int id, MenuItem menuItem);

    /**
     * Supprime un MenuItem en fonction de son id.
     *
     * @param id du MenuItem à supprimer.
     * @return true si la suppression a été effectuée avec succès, sinon false.
     */
    boolean deleteMenuItemById(int id);
}
