package com.example.tp_resto.controller;
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
                return ResponseEntity.ok("Il n'y aucune factures");
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

    @PostMapping("/add-factures/commande/{id}")
    public ResponseEntity<?> createFactureForCommande(@PathVariable int id) {
        try {
            Facture facture = factureService.createFactureExistingCommande(id);
            return  ResponseEntity.ok("Facture No " + facture.getId() + " a bien été enregistrée dans la " +
                    "commande No " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/updatefacture/{id}")
    public ResponseEntity<?> updateFactureById(@PathVariable int id, @RequestBody Facture facture)
    {
        if(facture == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veuillez fournir les modifications");
        }else{
            try {
                Facture facture1 = factureService.updateFacture(id, facture);
                if(facture1 !=null){
                    return ResponseEntity.ok(facture1);
                }else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Facture No "+id+" introuvable");
                }
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
    }

    @DeleteMapping("delFacture/{id}")
    public ResponseEntity<?> deleteFactureById(@PathVariable int id)  {
        try {
            boolean deleted = factureService.deleteFactureById(id);
            if (deleted) {
                return ResponseEntity.status(200).body("Facture No " + id + " Supprimée");
            } else {
                return ResponseEntity.status(400).body("Facture No " + id + " Non Supprimée");
            }
        }catch ( Exception ex) {
            String msg = ex.getMessage();
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Facture Non Supprimée, " + msg);
        }
    }
}