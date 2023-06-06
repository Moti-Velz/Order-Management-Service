package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.service.CommandeService;
import com.example.tp_resto.service.CommandeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ControlleurCommande {

    private CommandeService commandeService;

    @Autowired
    public ControlleurCommande(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping("/") // http://localhost:8080/
    public String sayHello() {
        return "Tu est dans le Controlleur Commande";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommandeById(@PathVariable int id) {
        Commande commande;
        try {
            commande = commandeService.getById(id);
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
    @PostMapping("/addCommandes")
    public ResponseEntity<?> addCommande(@RequestBody Commande newCommande) {
        Commande commande = null;
        try {
            commande = commandeService.createCommande(newCommande);
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
                Commande commande = commandeService.updateCommandeById(id, updatedCommande);
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
            boolean isDeleted = commandeService.deleteCommandeById(id);
            if (isDeleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete Commande");
        }
    }
    @GetMapping("/commandes")
    public List<Commande> getAllCommandes()
    {
        return this.commandeService.findAll();
    }





    //----------------------------------------------------------------------------------------//
    @GetMapping("/commandeById/get{id}")
    public ResponseEntity<Commande> getCommandeByIdtest(@PathVariable int id) {
        Commande commande = commandeService.getById(id);
        if (commande != null) {
            return ResponseEntity.ok(commande);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/test")
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande createdCommande = commandeService.createCommande(commande);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommande);
    }

    @PutMapping("/test{id}")
    public ResponseEntity<Commande> updateCommandeById(@PathVariable Integer id, @RequestBody Commande commande) {
        Commande updatedCommande = commandeService.updateCommandeById(id, commande);
        if (updatedCommande != null) {
            return ResponseEntity.ok(updatedCommande);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/test{id}")
    public ResponseEntity<Void> deleteCommandeById(@PathVariable Integer id) {
        boolean deleted = commandeService.deleteCommandeById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/test/get")
    public ResponseEntity<List<Commande>> getAllCommandesTest() {
        List<Commande> commandes = commandeService.findAll();
        return ResponseEntity.ok(commandes);
    }
}

