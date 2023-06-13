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

        @Autowired
        public ControlleurCommandeItem(CommandeItemService commandeItemService, CommandeService commandeService, MenuItemService menuItemService) {
            this.commandeItemService = commandeItemService;
            this.commandeService = commandeService;
        }




}
