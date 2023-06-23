package com.example.tp_resto.service;

import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.exception.MenuItemNotFoundException;
import com.example.tp_resto.repository.ICommandeItemRepo;
import com.example.tp_resto.repository.IMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service pour les objets MenuItem.
 * Cette classe fournit les services CRUD pour les objets MenuItem.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService{

    IMenuItem menuRepository;
    ICommandeItemRepo commandeItemRepo;


    /**
     * Constructeur de la classe MenuItemServiceImpl.
     *
     * @param menuRepository le dépôt pour les objets MenuItem.
     * @param commandeItemRepo le dépôt pour les objets CommandeItem.
     */
    @Autowired
    MenuItemServiceImpl(IMenuItem menuRepository, ICommandeItemRepo commandeItemRepo) {
        this.commandeItemRepo = commandeItemRepo;
        this.menuRepository=menuRepository;
    }

    /**
     * Récupère un MenuItem par son ID.
     *
     * @param id l'ID du MenuItem à récupérer.
     * @return le MenuItem correspondant à l'ID donné.
     */
    @Override
    public MenuItem getById(int id) {
        Optional<MenuItem> itemOpt = menuRepository.findById(id);
        MenuItem item = null;
        if(itemOpt.isPresent()) {
            item = itemOpt.get();
        } else {
            throw new MenuItemNotFoundException("On s'en fou de ce texte");
        }
        return item;
    }

    /**
     * Récupère un MenuItem par son nom.
     *
     * @param name le nom du MenuItem à récupérer.
     * @return le MenuItem correspondant au nom donné.
     */
    @Override
    public MenuItem getByName(String name) {
        return menuRepository.findByNameIgnoreCase(name);
    }

    /**
     * Récupère tous les MenuItem.
     *
     * @return une liste contenant tous les MenuItem.
     */
    @Override
    public List<MenuItem> findAll() {
        return menuRepository.findAll();
    }

    /**
     * Enregistre un nouveau MenuItem.
     *
     * @param menuItem le MenuItem à enregistrer.
     * @return le MenuItem enregistré.
     */
    @Override
    public MenuItem save(MenuItem menuItem) {
        Optional<MenuItem> optionalMenuItem = menuRepository.findMenuItemByName(menuItem.getName());

        if (optionalMenuItem.isPresent()) {

            throw new RuntimeException("Menu déjà existant");
        } else {
            return menuRepository.save(menuItem);
        }
    }
    /**
     * Met à jour un MenuItem par son ID.
     *
     * @param id l'ID du MenuItem à mettre à jour.
     * @param menuItem le MenuItem mis à jour.
     * @return le MenuItem mis à jour.
     */

    @Override
    public MenuItem updateMenuItemById(int id, MenuItem menuItem) {
        Optional<MenuItem> optionalMenuItem = menuRepository.findById(id);

        if (optionalMenuItem.isPresent()) {
            MenuItem existingItem = optionalMenuItem.get();

            if (existingItem.equals(menuItem)) {
                System.out.println("Impossible d'update menu existant");
                return existingItem;
            } else {
                existingItem.setName(menuItem.getName());
                existingItem.setPrice(menuItem.getPrice());
                existingItem.setDescription(menuItem.getDescription());

                return menuRepository.save(existingItem);
            }

        } else
            throw new MenuItemNotFoundException("MenuItem Not Found");
    }

    /**
     * Supprime un MenuItem par son ID.
     *
     * @param id l'ID du MenuItem à supprimer.
     * @return true si le MenuItem a été supprimé, sinon false.
     */

    @Override
    public boolean deleteMenuItemById(int id) {

        Optional<MenuItem> menuItemOptional = menuRepository.findById(id);
        if(!menuItemOptional.isPresent()) {
            throw new MenuItemNotFoundException("Article Introuvable");
        }

        List<CommandeItem> listeItem = commandeItemRepo.findCommandeItemByMenuItem_Id(id);
        if (!listeItem.isEmpty()) {
            listeItem.stream().forEach(item -> item.setMenuItem(null));
        }

        MenuItem menuItem = menuItemOptional.get();
        menuRepository.delete(menuItem);
        return true;
    }
}
