package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes")
public class ControlleurCommande {
//servir a starter la commande, add, gestion ect
    private final CommandeService commandeService;
    private final CommandeItemService itemService;

    @Autowired
    public ControlleurCommande(CommandeService commandeService, CommandeItemService itemService) {
        this.commandeService = commandeService;
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCommandes() {
        List<Commande> list = null;
        try {
            list = commandeService.getAll();
            if (list.isEmpty()) {
                return ResponseEntity.status(200).body("Aucune commandes présentes");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Commandes non trouvées");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommandeById(@PathVariable int id) {
        Commande commande = null;
        try {
            commande = commandeService.getById(id);
            if (commande != null) {
                return ResponseEntity.status(HttpStatus.FOUND).body(commande);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande non trouvée");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to retrieve Commande");
        }
    }

    @PostMapping("/{commandeId}")
    public CommandeItem addMenuItemToCommande(@PathVariable int commandeId, @RequestBody CommandeItem commandeItem) {

        Commande commande = commandeService.getById(commandeId);
        commandeService.addItemToCommande(commande, commandeItem);
        return commandeItem;
    }

    //Best Practices c'est de retourner l'objet créé
    //On va aussi faire une gestion d'exception plus pointue ici
    //Semble fonctionner, à tester
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande newCommande) {

        Commande savedCommande = null;
        try {
            List<CommandeItem> itemList = newCommande.getOrderItems();
            for(CommandeItem i : itemList){
                newCommande.addItem(i);
            }
             savedCommande = commandeService.saveCommande(newCommande);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(savedCommande, HttpStatus.CREATED);
    }
    @PostMapping("commandeAdd")
    public ResponseEntity<Commande> createCommande1(@RequestBody Commande commande, @RequestBody CommandeItem commandeItem)
    {
        List<CommandeItem> commandeItem1 = commande.getOrderItems();
        commande.setOrderItems(commandeItem1);
        Commande savedCommande = this.commandeService.saveCommande(commande);
        return new ResponseEntity<>(savedCommande, HttpStatus.CREATED);
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
                return ResponseEntity.ok("Commande "+id+" a ete supprimer avec success ! motherfucker");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande not found");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete Commande"+e);
        }
    }

}

