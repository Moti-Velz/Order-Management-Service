package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.Facture;
import com.example.tp_resto.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControlleurFacture {

    private FactureService factureService;

    @Autowired
    public ControlleurFacture(FactureService factureService) {
        this.factureService = factureService;
    }

        @GetMapping("/facture")
        public ResponseEntity<?> getAllFacture(){
            List<Facture> listFacture = factureService.findAll();
            if (listFacture.isEmpty()) {
                return ResponseEntity.ok("Il n'y a aucune Facture");
            }
            return ResponseEntity.ok(listFacture);
        }



    @GetMapping("/facture/{id}")
    public Facture getFactureById(@PathVariable int id) {
        try {
            return factureService.findById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facture introuvable");
        }
    }

    @PostMapping("/factures")
    public Facture createFacture(@RequestBody Commande commande)
    {
        Facture savedFacture = new Facture();
        savedFacture.setCommande(commande);

        factureService.saveFacture(savedFacture);
        return savedFacture;
    }

    @DeleteMapping("delFacture/{id}")
    public ResponseEntity<?> deleteFactureById(@PathVariable int id) {
            Facture facture = factureService.findById(id);
            boolean isDeleted = factureService.deleteFacture(facture);
            if (isDeleted) {
                return ResponseEntity.ok("Facture avec l'id: " + id + " a été supprimée avec succès!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Facture introuvable");
            }

    }


    @PutMapping("/updatefacture/{id}")
    public ResponseEntity<?> updateFactureById(@PathVariable int id, @RequestBody Facture facture)
    {
        if(facture == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veuillez fourner les modification");

        }else{
            try {
                Facture facture1 = factureService.updateFacture(id, facture);
                if(facture1 !=null){
                    return ResponseEntity.ok(facture1);
                }else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Facture avec Id: "+id+" introuvable");
                }
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Facture non modifier!");
            }
        }

    }
}


