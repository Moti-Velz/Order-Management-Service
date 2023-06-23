package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.Facture;
import com.example.tp_resto.exception.FactureNotFoundException;
import com.example.tp_resto.repository.ICommandeRepo;
import com.example.tp_resto.repository.IFactureRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service de Facture.
 * Cette classe fournit les méthodes CRUD pour les objets Facture.
 */
@Service
public class FactureServiceImpl implements FactureService {

    private final IFactureRepo factureRepo;
    private final ICommandeRepo commandeRepository;

    /**
     * Constructeur de la classe FactureServiceImpl.
     *
     * @param factureRepo le dépôt pour les objets Facture.
     * @param commandeRepository le dépôt pour les objets Commande.
     */
    @Autowired
    public FactureServiceImpl(IFactureRepo factureRepo, ICommandeRepo commandeRepository) {
        this.factureRepo = factureRepo;
        this.commandeRepository = commandeRepository;
    }

    /**
     * Enregistre une nouvelle Facture.
     *
     * @param facture la Facture à enregistrer.
     * @return la Facture enregistrée.
     */
    @Override
    public Facture saveFacture(Facture facture) {
        // Check if a Facture with the same commandeId already exists
        if (factureRepo.existsByCommandeId(facture.getCommande().getId())) {
            throw new IllegalArgumentException("Une facture associée à la commande No "
                    + facture.getCommande().getId() + " existe déjà.");
        }
        return factureRepo.save(facture);
    }

    /**
     * Crée une Facture pour une Commande donnée.
     *
     * @param commande la Commande pour laquelle créer une Facture.
     * @return la nouvelle Facture créée.
     */
    public Facture createFacture(Commande commande) {
        // Votre logique de création de facture ici
        // Vous pouvez utiliser le FactureRepository pour enregistrer la facture en base de données

        // Exemple simplifié pour créer une facture avec la commande
        Facture facture = new Facture();
        facture.setCommande(commande);
        return factureRepo.save(facture);
    }

    /**
     * Récupère une Facture en fonction de son identifiant.
     *
     * @param id l'identifiant de la Facture à récupérer.
     * @return la Facture correspondante à l'identifiant donné.
     */
    @Transactional
    @Override
    public Facture findById(int id) {
        return factureRepo.findById(id).get();
    }


    /**
     * Récupère toutes les Factures.
     *
     * @return une liste de toutes les Factures.
     */
    @Override
    public List<Facture> findAll() {

        List<Facture> maListe = factureRepo.findAll();
        if(maListe.isEmpty()) {
            throw new FactureNotFoundException("Aucune factures trouvées");
        } else return maListe;
    }

    /**
     * Récupère une liste de Factures en fonction de leur date.
     *
     * @param date la date des Factures à récupérer.
     * @return une liste de Factures correspondant à la date donnée.
     */
    @Override
    public List<Facture> findByDate(LocalDateTime date) {

        return factureRepo.findByBillTime(date);
    }

    /**
     * Met à jour une Facture en fonction de son id.
     *
     * @param id de la Facture à mettre à jour.
     * @param facture la nouvelle Facture à enregistrer.
     * @return la Facture mise à jour.
     */
    @Override
    public Facture updateFacture(int id, Facture facture) {

        //On verifie qu'une facture est deja dans la BD pour ce ID
        Optional<Facture> factureOptional = factureRepo.findById(id);

        //Si oui -> on la récupère
        //Si non -> lance une exception
        if (factureOptional.isEmpty()) {
            throw new FactureNotFoundException("Facture No " + id + " Non Trouvée");
        } else {
            Facture existingFacture = factureOptional.get();
            existingFacture.setStatus(facture.getStatus());
            existingFacture.setBillTime(facture.getBillTime());

                return factureRepo.save(existingFacture);
            }
    }

    /**
     * Supprime une Facture en fonction de son id.
     *
     * @param id de la Facture à supprimer.
     * @return true si la suppression a été effectuée avec succès, sinon false.
     */
    @Transactional
    @Override
    public boolean deleteFactureById(int id) {
        Optional<Facture> factureOpt = factureRepo.findById(id);
        if(factureOpt.isPresent()){
            Facture facture = factureOpt.get();
            Commande commande = facture.getCommande();

            if(commande != null) {
                if (commande.getFacture() != null) {
                    commande.removeFacture();
                    commandeRepository.save(commande);
                }
            }
            factureRepo.deleteById(id);
            return true;
        } else {
            throw new FactureNotFoundException("Facture No " + id + " Introuvable");
        }
    }

    /**
     * Crée une nouvelle Facture pour une Commande existante en fonction de son id.
     *
     * @param id de la Commande pour laquelle créer une nouvelle Facture.
     * @return la nouvelle Facture créée pour la Commande existante.
     */
    @Override
    public Facture createFactureExistingCommande(int id) {
        Optional<Commande> commandeOpt = commandeRepository.findById(id);
        Facture facture = new Facture();

        if(commandeOpt.isPresent()) {
        facture.setStatus(false);
        Commande commande = commandeOpt.get();

        commande.setFactureBidirection(facture);
        commandeRepository.save(commande);

        return factureRepo.findFactureByCommande_Id(id).get();
        } else throw new RuntimeException("Impossible d'associer une facture. Commande Introuvable");
    }
}
