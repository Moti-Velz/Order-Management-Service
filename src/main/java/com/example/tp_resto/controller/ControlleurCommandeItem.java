package com.example.tp_resto.controller;


import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import com.example.tp_resto.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commande-items")
public class ControlleurCommandeItem {

    private final CommandeItemService commandeItemService;
    private final CommandeService commandeService;

    @Autowired
    public ControlleurCommandeItem(CommandeItemService commandeItemService, CommandeService commandeService) {
        this.commandeItemService = commandeItemService;
        this.commandeService = commandeService;
    }
    @GetMapping
    public List<CommandeItem> getAllCommandeItems() {
        return commandeItemService.findAll();
    }

    @GetMapping("/commandes/{commandeId}")
    public List<CommandeItem> getCommandeItemsByCommande(@PathVariable int commandeId) {
        Commande commande = commandeService.getById(commandeId).orElseThrow(() -> new RuntimeException
                ("Commande No" + commandeId + " Introuvable"));

        return commandeItemService.findByCommande(commande);
    }

    @PutMapping("/{commandeItemId}")
    public CommandeItem updateCommandeItem(@PathVariable int commandeItemId,
                                           @RequestBody CommandeItem commandeItemDetails) {
        CommandeItem commandeItem = commandeItemService.findById(commandeItemId).orElseThrow(() -> new RuntimeException
                ("CommandeItem No" + commandeItemId + " Introuvable"));

        commandeItem.setQuantity(commandeItemDetails.getQuantity());
        commandeItem.setMenuItem(commandeItemDetails.getMenuItem());
        return commandeItemService.save(commandeItem);
    }

    @DeleteMapping("/{commandeItemId}")
    public ResponseEntity<?> deleteCommandeItem(@PathVariable(value = "commandeItemId") int commandeItemId) {
        CommandeItem commandeItem = commandeItemService.findById(commandeItemId).orElseThrow(() -> new RuntimeException
                ("CommandeItem No" + commandeItemId + " Introuvable"));

        commandeItemService.deleteItemFromOrderItems(commandeItem);
        return ResponseEntity.ok().body("CommandeItem No " + commandeItemId + " supprimé avec succès.");
    }
}