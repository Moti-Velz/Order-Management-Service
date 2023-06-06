package com.example.tp_resto.configuration;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.service.CommandeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ConfigStart implements CommandLineRunner {

    private CommandeService commandeService;

    public ConfigStart(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(commandeService.getAll().isEmpty()) {
            createCommandeWithItemsAndMenuItems(commandeService);
        }


    }

    private void createCommandeWithItemsAndMenuItems(CommandeService commandeService) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Commande tempCommande = new Commande(timestamp);

        CommandeItem commandeItem = new CommandeItem(2);

        MenuItem foodItem =  new MenuItem("Steak", "RibEye", 32.99);

        commandeItem.setMenuItem(foodItem);

        tempCommande.addItem(commandeItem);

        commandeService.saveCommande(tempCommande);

        System.out.println(tempCommande);

    }
}
