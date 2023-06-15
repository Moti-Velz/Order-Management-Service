package com.example.tp_resto.controller;


import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import com.example.tp_resto.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/commandeItems")
public class ControlleurCommandeItem {

        private final CommandeItemService commandeItemService;
        private final CommandeService commandeService;

        @Autowired
        public ControlleurCommandeItem(CommandeItemService commandeItemService, CommandeService commandeService, MenuItemService menuItemService) {
            this.commandeItemService = commandeItemService;
            this.commandeService = commandeService;
        }
}
