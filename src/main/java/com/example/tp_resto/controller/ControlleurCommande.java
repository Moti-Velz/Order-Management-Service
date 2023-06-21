package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


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

    @GetMapping("/commandes/getAll")
    public ResponseEntity<?> getAllCommandes() {
            List<Commande> list = commandeService.getAll();
            if( list == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Les Commandes sont Introuvables");
            }
            if (list.isEmpty()) {
                return ResponseEntity.status(200).body("Il y a Aucune commandes présentes a présenter");
            }
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @GetMapping("/commandes/get/{id}")
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il y a une erreur avec votre Requête, nous ne pouvons poursuivre votre demande.");
        }
    }

    @PostMapping("/commandes/add/{commandeId}")
    public Optional<Commande> addMenuItemToCommande(@PathVariable int commandeId, @RequestBody CommandeItem commandeItem) {

        try {
            Optional<Commande> commande = commandeService.getById(commandeId);
            if(!commande.isPresent()) {
                throw new RuntimeException("Commande Introuvable");
            }
            Commande commandePresente = commande.get();
            commandePresente.addItem(commandeItem);
            commandeService.saveCommande(commandePresente);

            return commandeService.getById(commandeId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un problème est survenu lors de l'ajout " +
                    "des items.");
        }
    }

    //Voir ce qu'on peut faire pour retourner le JSON avec les infos de MenuItem
    @PostMapping("/commandes/add")
    public Optional<Commande> createCommandeWithItems(@RequestBody List<CommandeItem> listeCommandeItem) {

        try {

        Commande commande = new Commande();

        for(CommandeItem item : listeCommandeItem) {
            commande.addItem(item);
        }
        Commande savedCommande = commandeService.saveCommande(commande);

        return commandeService.getById(savedCommande.getId());
        } catch ( Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un problème est survenu lors de la création " +
                    "de la commande");
        }
    }

    @PutMapping("/commandes/update/{id}")
    public ResponseEntity<?> updateCommandeById(@PathVariable int id, @RequestBody Commande updatedCommande) {

        if (updatedCommande == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tous les champs sont obligatoires pour poursuivre a la modification");
        } else {
            try {
                Commande commande = commandeService.updateCommandeById(id, updatedCommande);
                if (commande != null) {
                    return ResponseEntity.ok(commande);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande non trouvée, impossible de la modifier.");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Une erreur est subvenue, la Commande ne sera pas Modifiée.");
            }
        }
    }

    @DeleteMapping("/commandes/del/{id}")
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

