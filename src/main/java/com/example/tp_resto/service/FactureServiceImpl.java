package com.example.tp_resto.service;

import com.example.tp_resto.entity.Facture;
import com.example.tp_resto.exception.FactureNotFoundException;
import com.example.tp_resto.repository.IFactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class FactureServiceImpl implements FactureService {

    private final IFactureRepo factureRepo;

    @Autowired
    public FactureServiceImpl(IFactureRepo factureRepo) {
        this.factureRepo = factureRepo;
    }

    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepo.save(facture);
    }

    @Override
    public Facture updateFacture(int id, Facture facture) {

        Optional<Facture> factureOptional = factureRepo.findById(id);
        Facture existingFacture = null;
        if (factureOptional.isPresent()) {
            existingFacture = factureOptional.get();

            existingFacture.setCommande(facture.getCommande());
            existingFacture.setStatus(facture.getStatus());
            existingFacture.setBillTime(facture.getBillTime());
            existingFacture.setPrix(facture.getPrix());

            return factureRepo.save(existingFacture);
        } else {
            throw new FactureNotFoundException("Facture Not Found");
        }

    }

    @Override
    public Facture findById(int id) {
        return factureRepo.findById(id)
                .orElseThrow(() -> new FactureNotFoundException("Facture Not Found"));
    }


    @Override
    public Facture findByDate(Date date) {
        return null;
    }

    @Override
    public void deleteFacture(Facture facture) {
        factureRepo.delete(facture);
    }

    @Override
    public void deleteFactureById(int id) {
        factureRepo.deleteById(id);
    }
}
