package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import com.example.tp_resto.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/commandeItems")
public class ControlleurCommandeItem {

        private final CommandeItemService commandeItemService;
        private final CommandeService commandeService;
        private final MenuItemService menuItemService;

        @Autowired
        public ControlleurCommandeItem(CommandeItemService commandeItemService, CommandeService commandeService, MenuItemService menuItemService) {
            this.commandeItemService = commandeItemService;
            this.commandeService = commandeService;
            this.menuItemService = menuItemService;
        }

        @GetMapping
        ResponseEntity<Commande> publicTestChunk() {

            int menuItemId = 3;
            int quantity = 2;
            int commandeId = 1;
            // Fetch the Commande and MenuItem entities
            MenuItem menuItem = menuItemService.getById(menuItemId);

            // Create a new CommandeItem and set the fetched entities and quantity
            CommandeItem commandeItem = new CommandeItem(quantity);
            commandeItem.setMenuItem(menuItem);

            Commande commande = commandeService.getById(commandeId);

            commande.addItem(commandeItem);
            commandeItem.setMenuItem(menuItem);
            commandeItem.setQuantity(quantity);

            // Save the CommandeItem using the service
            Commande savedCommande = commandeService.saveCommande(commande);


            // Return the saved CommandeItem as the response
            return ResponseEntity.ok(savedCommande);
        }

    @PostMapping
        public ResponseEntity<Commande> createCommandeItem
            (@RequestBody Map<String, Integer> body) {

            int commandeId = body.get("commandeId");
            int menuItemId = body.get("menuItemId");
            int quantity = body.get("quantity");


            // Fetch the Commande and MenuItem entities
            MenuItem menuItem = menuItemService.getById(menuItemId);

            // Create a new CommandeItem and set the fetched entities and quantity
            CommandeItem commandeItem = new CommandeItem(quantity);
            commandeItem.setMenuItem(menuItem);

            Commande commande = commandeService.getById(commandeId);

            commande.addItem(commandeItem);
            commandeItem.setMenuItem(menuItem);
            commandeItem.setQuantity(quantity);

            // Save the CommandeItem using the service
            Commande savedCommande = commandeService.saveCommande(commande);


            // Return the saved CommandeItem as the response
            return ResponseEntity.ok(savedCommande);
        }

    //@PostMapping("/")

    @PostMapping("/commande/{commandeId}")
        public CommandeItem addMenuItemToCommande(@PathVariable int commandeId, @RequestBody CommandeItem commandeItem) {

            Commande commande = commandeService.getById(commandeId);
            commandeService.addItemToCommande(commande, commandeItem);
            return commandeItem;
    }

}
