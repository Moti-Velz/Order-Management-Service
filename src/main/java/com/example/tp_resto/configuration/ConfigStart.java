package com.example.tp_resto.configuration;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.Facture;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.service.CommandeService;
import com.example.tp_resto.service.MenuItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConfigStart implements CommandLineRunner {

    private final CommandeService commandeService;
    private final MenuItemService menuItemService;

    public ConfigStart(CommandeService commandeService, MenuItemService menuItemService) {
        this.commandeService = commandeService;
        this.menuItemService = menuItemService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(commandeService.getAll().isEmpty()) {
            remplirBD(commandeService, menuItemService);
        }


    }

    private void remplirBD(CommandeService commandeService, MenuItemService menuItemService) {

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

        this.menuItemService.save(foodItem);
        this.menuItemService.save(foodItem2);
        this.menuItemService.save(foodItem3);
        this.menuItemService.save(foodItem4);
        this.menuItemService.save(foodItem5);
        this.menuItemService.save(foodItem6);
        this.menuItemService.save(foodItem7);
        this.menuItemService.save(foodItem8);
        this.menuItemService.save(foodItem9);
        this.menuItemService.save(foodItem10);


        CommandeItem commandeItem = new CommandeItem(2);

        commandeItem.setMenuItem(foodItem);

        Commande tempCommande = new Commande();

        tempCommande.addItem(commandeItem);


        //Bouton Checkout
        Facture facture = new Facture();

        facture.setStatus(false); // example status

        tempCommande.setFactureBidirection(facture);
        commandeService.saveCommande(tempCommande);
        System.out.println(tempCommande);
        System.out.println("==================");

        System.out.println("Insertion de 5 exemples de commande");

        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem("Tofu", "Grilled Tofu", 18.99));
        menuItems.add(new MenuItem("Rice", "Basmati Rice", 12.99));
        menuItems.add(new MenuItem("Noodles", "Hakka Noodles", 14.99));
        menuItems.add(new MenuItem("Salad", "Green Salad", 10.99));
        menuItems.add(new MenuItem("Soup", "Hot and Sour Soup", 9.99));

        menuItems.forEach(this.menuItemService::save);

        for (int i = 0; i < 5; i++) {
            Commande newCommande = new Commande();

            for (int j = 0; j < 5; j++) {
                CommandeItem newCommandeItem = new CommandeItem((j + 1) * (i + 1));
                newCommandeItem.setMenuItem(menuItems.get(i));
                newCommande.addItem(newCommandeItem);
            }

            Facture newFacture = new Facture();
            newFacture.setStatus(false); // Statut non Payé

            newCommande.setFactureBidirection(newFacture);
            commandeService.saveCommande(newCommande);

            System.out.println(newCommande);
        }
        System.out.println("==================");



    }
}
