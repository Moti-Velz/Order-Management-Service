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
            throw new IllegalArgumentException("Facture with commande ID " + facture.getCommande().getId() + " already exists.");
        }
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
        } else  return maListe;
    }


    @Override
    public List<Facture> findByDate(LocalDateTime date) { //TODO

        return factureRepo.findByBillTime(date);
    }

    //Update
    @Override
    public Facture updateFacture(int id, Facture facture) {

        Optional<Facture> factureOptional = factureRepo.findById(id);

        if (factureOptional.isPresent()) {
            Facture existingFacture = factureOptional.get();

            //On verifie si on essaie de changer l'assignation a une commandes différente
            boolean facturesAssignesADesCommandesDifferentes = !existingFacture.getCommande().getId()
                    .equals(facture.getCommande().getId());


            boolean factureEstDejaAssigneAUneCommande = factureRepo.existsByCommandeId(facture.getCommande().getId());

            if (factureEstDejaAssigneAUneCommande && facturesAssignesADesCommandesDifferentes) {
                throw new IllegalArgumentException("Facture reliée avec commande ID " + facture.getCommande().getId()
                        + " existe déja.");
            }

                Commande commande = facture.getCommande();
                commande.setFacture(facture);
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
}
