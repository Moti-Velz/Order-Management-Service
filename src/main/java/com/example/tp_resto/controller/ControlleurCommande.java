package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.service.CommandeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commandes")
public class ControlleurCommande {

    private final CommandeServiceImpl commandeServiceImpl;

    @Autowired
    public ControlleurCommande(CommandeServiceImpl commandeServiceImpl) {
        this.commandeServiceImpl = commandeServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommandeById(@PathVariable int id) {
        Commande commande;
        try {
            commande = commandeServiceImpl.getById(id);
            if (commande != null) {
                return ResponseEntity.ok(commande);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande non trouvée");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to retrieve Commande");
        }

    }

    //Best Practices c'est de retourner l'objet créé
    //On va aussi faire une gestion d'exception plus pointue ici
    @PostMapping
    public ResponseEntity<?> addCommande(@RequestBody Commande newCommande) {
        Commande commande = null;
        try {
            commande = commandeServiceImpl.createCommande(newCommande);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Commande non créée");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(commande + "créée");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommandeById(@PathVariable int id, @RequestBody Commande updatedCommande) {

        if (updatedCommande == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tous les champs sont obligatoires");
        } else {
            try {
                Commande commande = commandeServiceImpl.updateCommandeById(id, updatedCommande);
                if (commande != null) {
                    return ResponseEntity.ok(commande);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande non trouvée");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Commande non modifiée");
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommandeById(@PathVariable int id) {
        try {
            boolean isDeleted = commandeServiceImpl.deleteCommandeById(id);
            if (isDeleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete Commande");
        }
    }
}

