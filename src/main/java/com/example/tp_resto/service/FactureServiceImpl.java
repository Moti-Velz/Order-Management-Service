package com.example.tp_resto.service;

import com.example.tp_resto.entity.Facture;
import com.example.tp_resto.exception.FactureNotFoundException;
import com.example.tp_resto.repository.IFactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FactureServiceImpl implements FactureService {

    private final IFactureRepo factureRepo;

    @Autowired
    public FactureServiceImpl(IFactureRepo factureRepo) {
        this.factureRepo = factureRepo;
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
    @Override
    public Facture findById(int id) {
        return factureRepo.findById(id)
                .orElseThrow(() -> new FactureNotFoundException("Facture No " + id + " Not Found"));
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
    public Facture findByDate(Date date) { //TODO
        return null;
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

                existingFacture.setCommande(facture.getCommande());
                existingFacture.setStatus(facture.getStatus());
                existingFacture.setBillTime(facture.getBillTime());

                return factureRepo.save(existingFacture);
            } else {
                throw new FactureNotFoundException("Facture No " + id + " Not Found");
            }
        }


    //Delete
    @Override
    public void deleteFacture(Facture facture) {
        Optional<Facture> factureOpt = factureRepo.findById(facture.getId());
        if(factureOpt.isPresent()){

        factureRepo.delete(facture);
        } else {
            throw new FactureNotFoundException("Facture No " + facture.getId() + " Not Found");
        }
    }

    //condition de doublons
    @Override
    public void deleteFactureById(int id) {
        Optional<Facture> factureOpt = factureRepo.findById(id);
        if(factureOpt.isPresent()){

            factureRepo.deleteById(id);
        } else {
            throw new FactureNotFoundException("Facture No " + id + " Not Found");
        }
    }
}
