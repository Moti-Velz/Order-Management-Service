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

@Service
public class FactureServiceImpl implements FactureService {

    private final IFactureRepo factureRepo;
    private final ICommandeRepo commandeRepository;

    @Autowired
    public FactureServiceImpl(IFactureRepo factureRepo, ICommandeRepo commandeRepository) {
        this.factureRepo = factureRepo;
        this.commandeRepository = commandeRepository;
    }

    //Create
    @Override
    public Facture saveFacture(Facture facture) {
        // Check if a Facture with the same commandeId already exists
        if (factureRepo.existsByCommandeId(facture.getCommande().getId())) {
            throw new IllegalArgumentException("Facture with commande ID "
                    + facture.getCommande().getId() + " already exists.");
        }
        return factureRepo.save(facture);
    }

    public Facture createFacture(Commande commande) {
        // Votre logique de création de facture ici
        // Vous pouvez utiliser le FactureRepository pour enregistrer la facture en base de données

        // Exemple simplifié pour créer une facture avec la commande
        Facture facture = new Facture();
        facture.setCommande(commande);
        return factureRepo.save(facture);
    }

    //Read
    @Transactional
    @Override
    public Facture findById(int id) {
        return factureRepo.findById(id).get();
    }


    //condition de doublons
    @Override
    public List<Facture> findAll() {

        List<Facture> maListe = factureRepo.findAll();
        if(maListe.isEmpty()) {
            throw new FactureNotFoundException("Aucune factures trouvées");
        } else return maListe;
    }


    @Override
    public List<Facture> findByDate(LocalDateTime date) { //TODO

        return factureRepo.findByBillTime(date);
    }

    /**
     * ici on veut verifier qu'on ne lie pas plus d'une facture a la meme commande
     * On veut egalement verifier si la commande possede deja une facture sinon on lance une exception
     * A terminer
     * @param id
     * @param facture
     * @return
     */
    @Override
    public Facture updateFacture(int id, Facture facture) {

        Optional<Facture> factureOptional = factureRepo.findById(id);

        if (factureOptional.isPresent()) {
            Facture existingFacture = factureOptional.get();

            if(existingFacture.getCommande() != null) {
                Optional<Commande> commandeOpt = commandeRepository.findByFacture_Id(facture.getId());
                if(commandeOpt.isPresent()) {
                    commandeOpt.get().setFactureBidirection(existingFacture);
                }

            }

                Commande commande = facture.getCommande();
                commande.setFactureBidirection(facture);
                existingFacture.setStatus(facture.getStatus());
                existingFacture.setBillTime(facture.getBillTime());

                return factureRepo.save(existingFacture);
            } else {
                throw new FactureNotFoundException("Facture No " + id + " Not Found");
            }
        }

    //condition de doublons
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
            System.out.println("Facture with ID " + id + " deleted successfully");

            return true;

        } else {
            throw new FactureNotFoundException(" Facture No " + id + " Not Found");
        }
    }

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
