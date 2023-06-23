package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Cette interface déclare les opérations de la base de données pour les objets Facture.
 * Elle étend JpaRepository, ce qui signifie qu'elle hérite d'une variété de méthodes pour travailler avec la persistance des données.
 */
public interface IFactureRepo extends JpaRepository<Facture, Integer> {
    /**
     * Vérifie si une facture existe avec un identifiant de commande spécifique.
     *
     * @param id L'identifiant de la commande à vérifier.
     * @return vrai si une facture existe avec l'identifiant de commande spécifié, faux sinon.
     */
    boolean existsByCommandeId(Integer id);
    /**
     * Recherche une facture par l'identifiant de commande.
     *
     * @param id L'identifiant de la commande pour laquelle trouver la facture.
     * @return Un Optional contenant la facture si elle est trouvée, sinon un Optional vide.
     */
    Optional<Facture> findFactureByCommande_Id(Integer id);

    /**
     * Recherche toutes les factures par date et heure de facturation.
     *
     * @param billTime La date et l'heure de facturation pour lesquelles trouver les factures.
     * @return Une liste des factures qui correspondent à la date et à l'heure de facturation spécifiées.
     */
    List<Facture> findByBillTime(LocalDateTime billTime);
    }

