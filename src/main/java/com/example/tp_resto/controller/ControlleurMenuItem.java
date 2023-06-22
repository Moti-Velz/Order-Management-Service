package com.example.tp_resto.controller;
import com.example.tp_resto.entity.MenuItem;

import com.example.tp_resto.exception.MenuItemNotFoundException;
import com.example.tp_resto.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
/**
 * Cette classe est un contrôleur REST pour les opérations liées aux éléments de menu.
 */
@RestController
public class ControlleurMenuItem {

    private final MenuItemService menuItemService;

    /**
     * Constructeur de la classe ControlleurMenuItem.
     *
     * @param menuItemService Le service de gestion des éléments de menu.
     */
    @Autowired
    public ControlleurMenuItem(MenuItemService menuItemService){
        this.menuItemService = menuItemService;
    }

    /**
     * Récupère tous les éléments de menu.
     *
     * @return ResponseEntity contenant la liste des éléments de menu ou un message indiquant l'absence d'éléments de menu.
     */
    @GetMapping("/menuitems")
    public ResponseEntity<?> getAllMenuItems(){
        List<MenuItem> List = menuItemService.findAll();
        if(List.isEmpty()){
            return ResponseEntity.ok("Le menu est vide");
        }
        return ResponseEntity.ok(List);
    }

    /**
     * Récupère un élément de menu par son ID.
     *
     * @param id L'ID de l'élément de menu à récupérer.
     * @return L'élément de menu correspondant ou une exception si l'élément de menu n'est pas trouvé.
     */
    //L'exception est lancée au niveau du service implémenté
    @GetMapping("/menuitems/{id}")
    public MenuItem getMenuItemById(@PathVariable int id){
        try{
            return menuItemService.getById(id);
        }catch(MenuItemNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MenuItem Introuvable");
        }
    }

    /**
     * Ajoute un nouvel élément de menu.
     *
     * @param newMenuItem Le nouvel élément de menu à ajouter.
     * @return ResponseEntity indiquant le succès ou l'échec de l'ajout de l'élément de menu.
     */
    @PostMapping("/menuitems/add")
    public ResponseEntity<?> addMenuItem(@RequestBody MenuItem newMenuItem) {
        MenuItem menuItem = null;
        try {
            menuItem = menuItemService.save(newMenuItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MenuItem non créée");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem.getName() + " créée");
    }

    /**
     * Met à jour un élément de menu existant.
     *
     * @param id       L'ID de l'élément de menu à mettre à jour.
     * @param menuItem Les détails de l'élément de menu mis à jour.
     * @return ResponseEntity contenant le message de succès ou une exception si la mise à jour échoue.
     */
    @PutMapping("/menuitems/update/{id}")
    public ResponseEntity<?> updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem) {

        try {
        MenuItem newMenuItem = menuItemService.updateMenuItemById(id, menuItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(newMenuItem.getName() + " mis a jour");

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible d'effectuer cette modification");
        }
    }

    /**
     * Supprime un élément de menu par son ID.
     *
     * @param id L'ID de l'élément de menu à supprimer.
     * @return ResponseEntity indiquant le succès ou l'échec de la suppression de l'élément de menu.
     */
    @DeleteMapping("/menuitems/del/{id}")
    public ResponseEntity <String> deleteMenuItemById(@PathVariable int id) {
        try {
            boolean deleted = menuItemService.deleteMenuItemById(id);
            if(deleted){
                return ResponseEntity.ok("MenuItem avec id " + id + " supprimé");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MenuItem avec id " + id + "introuvable.");
            }
        } catch (MenuItemNotFoundException mnfex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article Introuvable");
        }
    }
}
