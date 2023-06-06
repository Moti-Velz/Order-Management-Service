package com.example.tp_resto.config;

import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import com.example.tp_resto.service.FactureService;
import com.example.tp_resto.service.MenuItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigStart implements CommandLineRunner {

    private CommandeService commandeService;
    private CommandeItemService commandeItemService;
    private FactureService factureService;
    private MenuItemService menuItemService;

    public ConfigStart(CommandeService commandeService, CommandeItemService commandeItemService, FactureService factureService, MenuItemService menuItemService) {
        this.commandeService = commandeService;
        this.commandeItemService = commandeItemService;
        this.factureService = factureService;
        this.menuItemService = menuItemService;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
