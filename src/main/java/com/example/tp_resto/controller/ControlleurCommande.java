package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ControlleurCommande {
//servir a starter la commande, add, gestion ect
    private final CommandeService commandeService;
    private final CommandeItemService itemService;

    @Autowired
    public ControlleurCommande(CommandeService commandeService, CommandeItemService itemService) {
        this.commandeService = commandeService;
        this.itemService = itemService;
    }

    @GetMapping("/commandes")
    public ResponseEntity<?> getAllCommandes() {
            List<Commande> list = commandeService.getAll();
            if( list == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Commandes Introuvables");
            }
            if (list.isEmpty()) {
                return ResponseEntity.status(200).body("Aucune commandes présentes");
            }
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @GetMapping("/commandes/{id}")
    public ResponseEntity<?> getCommandeById(@PathVariable int id) {
        Optional<Commande> commande = null;
        try {
             commande = commandeService.getById(id);
            if (commande.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(commande);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande No " + id + " Introuvable");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Commande No " + id + " Introuvable");
        }
    }

    @PostMapping("/commandes/{commandeId}")
    public CommandeItem addMenuItemToCommande(@PathVariable int commandeId, @RequestBody CommandeItem commandeItem) {

        Optional<Commande> commande = commandeService.getById(commandeId);
        if(!commande.isPresent()) {
            throw new RuntimeException("Commande Introuvable");
        }
        Commande commandePresente = commande.get();
        commandePresente.addItem(commandeItem);
        commandeService.saveCommande(commandePresente);
        return commandeItem;
    }

    //on pourrait utiliser cette methode pour envoyer une liste dèitem et de creer la commande a ce moment
    @Transactional
    @PostMapping("/creation-commande")
    public Commande createCommandeWithItems(@RequestBody List<CommandeItem> listeCommandeItem) {

        Commande commande = new Commande();

        for(CommandeItem item : listeCommandeItem) {
            commande.addItem(item);
        }
        Commande savedCommande = commandeService.saveCommande(commande);

        return savedCommande;
    }

    @PutMapping("/commandes/{id}")
    public ResponseEntity<?> updateCommandeById(@PathVariable int id, @RequestBody Commande updatedCommande) {

        if (updatedCommande == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tous les champs sont obligatoires");
        } else {
            try {
                Commande commande = commandeService.updateCommandeById(id, updatedCommande);
                if (commande != null) {
                    return ResponseEntity.ok(commande);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande non trouvée");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Commande Non Modifiée.");
            }
        }
    }

    @DeleteMapping("/commandes/{id}")
    public ResponseEntity<?> deleteCommandeById(@PathVariable int id) {
        try {
            boolean isDeleted = commandeService.deleteCommandeById(id);
            if (isDeleted) {
                return ResponseEntity.ok("Commande " + id + " à été supprimé avec succès !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Commande Non Supprimée");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un problème est survenu lors de la supression de " +
                "la commande No " + id);
    }
}

