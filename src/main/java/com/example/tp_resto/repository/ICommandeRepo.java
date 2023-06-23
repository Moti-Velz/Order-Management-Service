package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Cette interface déclare les opérations de la base de données pour les objets de type Commande.
 * Elle étend JpaRepository, ce qui signifie qu'elle hérite d'une variété de méthodes pour travailler avec la persistance des données.
 */
public interface ICommandeRepo extends JpaRepository<Commande, Integer> {

}
