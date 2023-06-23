package com.example.tp_resto.repository;

import com.example.tp_resto.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Cette interface déclare les opérations de la base de données pour les objets MenuItem.
 * Elle étend JpaRepository, ce qui signifie qu'elle hérite d'une variété de méthodes pour travailler avec la persistance des données.
 */
public interface IMenuItem extends JpaRepository<MenuItem, Integer> {
    /**
     * Recherche un élément de menu par son nom, sans tenir compte de la casse.
     *
     * @param name Le nom de l'élément de menu à rechercher.
     * @return L'élément de menu trouvé, ou null si aucun élément de menu avec ce nom n'a été trouvé.
     */
    MenuItem findByNameIgnoreCase(String name);
    /**
     * Recherche un élément de menu par son nom.
     *
     * @param name Le nom de l'élément de menu à rechercher.
     * @return Un Optional contenant l'élément de menu s'il est trouvé, sinon un Optional vide.
     */
    Optional <MenuItem> findMenuItemByName(String name);

}
