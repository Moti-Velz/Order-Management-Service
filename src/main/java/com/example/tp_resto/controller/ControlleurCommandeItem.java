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

/**
 * Cette classe est un contrôleur REST pour les opérations liées aux éléments de commande.
 */
@RestController
@RequestMapping("/commande-items")
public class ControlleurCommandeItem {

    private final CommandeItemService commandeItemService;
    private final CommandeService commandeService;

    /**
     * Constructeur de la classe ControlleurCommandeItem.
     *
     * @param commandeItemService Le service de gestion des éléments de commande.
     * @param commandeService     Le service de gestion des commandes.
     */
    @Autowired
    public ControlleurCommandeItem(CommandeItemService commandeItemService, CommandeService commandeService) {
        this.commandeItemService = commandeItemService;
        this.commandeService = commandeService;
    }

    /**
     * Récupère tous les éléments de commande.
     *
     * @return Liste des éléments de commande.
     */
    @GetMapping
    public List<CommandeItem> getAllCommandeItems() {
        return commandeItemService.findAll();
    }

    /**
     * Récupère les éléments de commande d'une commande spécifique.
     *
     * @param commandeId L'ID de la commande.
     * @return Liste des éléments de commande de la commande spécifiée.
     */
    @GetMapping("/getcommandes/{commandeId}")
    public List<CommandeItem> getCommandeItemsByCommande(@PathVariable int commandeId) {
        Commande commande = commandeService.getById(commandeId).orElseThrow(() -> new RuntimeException
                ("Commande No" + commandeId + " Introuvable"));

        return commandeItemService.findByCommande(commande);
    }

    /**
     * Met à jour un élément de commande.
     *
     * @param commandeItemId     L'ID de l'élément de commande à mettre à jour.
     * @param commandeItemDetails Les détails de l'élément de commande mis à jour.
     * @return L'élément de commande mis à jour.
     */
    @PutMapping("/update/{commandeItemId}")
    public CommandeItem updateCommandeItem(@PathVariable int commandeItemId,
                                           @RequestBody CommandeItem commandeItemDetails) {

        CommandeItem commandeItem = commandeItemService.findCommandeItemById(commandeItemId).orElseThrow(() -> new RuntimeException
                ("CommandeItem No" + commandeItemId + " Introuvable"));

        commandeItem.setQuantity(commandeItemDetails.getQuantity());
        commandeItem.setMenuItem(commandeItemDetails.getMenuItem());
        return commandeItemService.saveCommandeItem(commandeItem);
    }

    @PutMapping("/update2/{commandeItemId}")
    public ResponseEntity<?> updateCommandeItem2(@PathVariable int commandeItemId,
                                                @RequestBody CommandeItem commandeItemDetails) {
        if (commandeItemDetails == null || commandeItemDetails.getQuantity() == 0
                || commandeItemDetails.getMenuItem() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veuillez fournir les parametres pour l'update");
        }

        CommandeItem commandeItem = commandeItemService.findCommandeItemById(commandeItemId)
                .orElseThrow(() -> new RuntimeException
                        ("CommandeItem No" + commandeItemId + " Introuvable"));

        commandeItem.setQuantity(commandeItemDetails.getQuantity());
        commandeItem.setMenuItem(commandeItemDetails.getMenuItem());

        CommandeItem updatedCommandeItem = commandeItemService.saveCommandeItem(commandeItem);

        if (updatedCommandeItem != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedCommandeItem);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Update CommandeItem impossible");
        }
    }



    /**
     * Supprime un élément de commande.
     *
     * @param commandeItemId L'ID de l'élément de commande à supprimer.
     * @return ResponseEntity indiquant le succès ou l'échec de la suppression.
     */
    @DeleteMapping("/del/{commandeItemId}")
    public ResponseEntity<?> deleteCommandeItem(@PathVariable(value = "commandeItemId") int commandeItemId) {
        CommandeItem commandeItem = commandeItemService.findCommandeItemById(commandeItemId).orElseThrow(() -> new RuntimeException
                ("CommandeItem No" + commandeItemId + " Introuvable"));

        commandeItemService.deleteItemFromOrderItems(commandeItem);
        return ResponseEntity.ok().body("CommandeItem No " + commandeItemId + " supprimé avec succès.");
    }
}