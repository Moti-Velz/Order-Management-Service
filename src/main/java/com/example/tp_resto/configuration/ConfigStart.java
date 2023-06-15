package com.example.tp_resto.configuration;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.Facture;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.service.CommandeService;
import com.example.tp_resto.service.FactureService;
import com.example.tp_resto.service.MenuItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class ConfigStart implements CommandLineRunner {

    private final CommandeService commandeService;
    private final MenuItemService menuItemService;

    private final FactureService factureService;

    public ConfigStart(CommandeService commandeService, MenuItemService menuItemService, FactureService factureService) {
        this.commandeService = commandeService;
        this.menuItemService = menuItemService;
        this.factureService = factureService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(commandeService.getAll().isEmpty()) {
            createCommandeWithItemsAndMenuItemsAndFacture(commandeService);
        }


    }

    private void createCommandeWithItemsAndMenuItemsAndFacture(CommandeService commandeService) {

        MenuItem foodItem =  new MenuItem("Steak", "RibEye", 32.99);
        MenuItem foodItem2 = new MenuItem("Poulet", "Poulet rôti avec des herbes provençales", 28.99);
        MenuItem foodItem3 = new MenuItem("Canard", "Canard à l'orange", 38.99);
        MenuItem foodItem4 = new MenuItem("Boeuf", "Steak de bœuf sauce au poivre", 35.99);
        MenuItem foodItem5 = new MenuItem("Fruits de Mer", "Plateau de fruits de mer variés", 50.99);
        MenuItem foodItem6 = new MenuItem("Salade", "Salade Niçoise", 24.99);
        MenuItem foodItem7 = new MenuItem("Poisson", "Filet de saumon grillé", 30.99);
        MenuItem foodItem8 = new MenuItem("Dessert", "Tarte Tatin aux pommes", 10.99);
        MenuItem foodItem9 = new MenuItem("Fromage", "Assortiment de fromages français", 20.99);
        MenuItem foodItem10 = new MenuItem("Soupe", "Bouillabaisse de Marseille", 25.99);

        menuItemService.save(foodItem);
        menuItemService.save(foodItem2);
        menuItemService.save(foodItem3);
        menuItemService.save(foodItem4);
        menuItemService.save(foodItem5);
        menuItemService.save(foodItem6);
        menuItemService.save(foodItem7);
        menuItemService.save(foodItem8);
        menuItemService.save(foodItem9);
        menuItemService.save(foodItem10);


        CommandeItem commandeItem = new CommandeItem(2);

        commandeItem.setMenuItem(foodItem);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Commande tempCommande = new Commande(timestamp);

        tempCommande.addItem(commandeItem);


        //Bouton Checkout
        Facture facture = new Facture();

        facture.setStatus(false); // example status
        LocalDateTime instant = LocalDateTime.now();
        facture.setBillTime(instant); // set current date and time
        tempCommande.setFacture(facture);
        commandeService.saveCommande(tempCommande);
        System.out.println(tempCommande);
        System.out.println("==================");

//        for(CommandeItem items : savedFacture.getCommande().getOrderItems()) {
//            items.getMenuItem();
//        }

    }
}
