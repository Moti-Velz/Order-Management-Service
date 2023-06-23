package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Cette interface déclare les opérations de la base de données pour les objets CommandeItem.
 * Elle étend JpaRepository, ce qui signifie qu'elle hérite d'une variété de méthodes pour travailler avec la persistance des données.
 */
public interface ICommandeItemRepo extends JpaRepository<CommandeItem, Integer> {

    /**
     * Recherche tous les éléments de commande associés à une commande spécifique.
     *
     * @param theCommande La commande pour laquelle trouver les éléments de commande.
     * @return Une liste des éléments de commande associés à la commande donnée.
     */
    List<CommandeItem> findByCommande(Commande theCommande);

    /**
     * Recherche tous les éléments de commande associés à un identifiant de MenuItem spécifique.
     *
     * @param id L'identifiant du MenuItem pour lequel trouver les éléments de commande.
     * @return Une liste des éléments de commande associés à l'identifiant du MenuItem donné.
     */
    List<CommandeItem> findCommandeItemByMenuItem_Id(Integer id);
}
