package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import com.example.tp_resto.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commandesItem")
public class ControlleurCommandeItem {
    private CommandeService commandeService;
private CommandeItemService commandeItemService;
private MenuItemService menuItemService;

@Autowired
public ControlleurCommandeItem(CommandeItemService commandeItemService, MenuItemService menuItemService) {
        this.commandeItemService = commandeItemService;
        this.menuItemService = menuItemService;
    }

    @GetMapping("/commandeItemTest")
    public String sayHello(){return "tu est dans : CommandeItem";}

    @PostMapping("/commandeItemAdd")
    public ResponseEntity<CommandeItem> addCommandeItem(@RequestBody Commande commande, CommandeItem commandeItem)
    {
        MenuItem menuItem = commandeItem.getMenuItem();
        commandeItem.setMenuItem(menuItem);
        CommandeItem commandeItemSaved = this.commandeItemService.addCommandeItem(commandeItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeItemSaved);
    }
}
