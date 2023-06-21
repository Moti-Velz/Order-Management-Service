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

@RestController
public class ControlleurMenuItem {

    private final MenuItemService menuItemService;

    @Autowired
    public ControlleurMenuItem(MenuItemService menuItemService){
        this.menuItemService = menuItemService;
    }


    @GetMapping("/menuitems")
    public ResponseEntity<?> getAllMenuItems(){
        List<MenuItem> List = menuItemService.findAll();
        if(List.isEmpty()){
            return ResponseEntity.ok("Le menu est vide");
        }
        return ResponseEntity.ok(List);
    }

    //L'exception est lancée au niveau du service implémenté
    @GetMapping("/menuitems/{id}")
    public MenuItem getMenuItemById(@PathVariable int id){
        try{
            return menuItemService.getById(id);
        }catch(MenuItemNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MenuItem Introuvable");
        }
    }

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

    //Faire la logique d'exception en fonction du type d'exception (not found / doublon)
    //fonctionne
    @PutMapping("/menuitems/update/{id}")
    public ResponseEntity<?> updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem) {

        try {
        MenuItem newMenuItem = menuItemService.updateMenuItemById(id, menuItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(newMenuItem.getName() + " mis a jour");

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible d'effectuer cette modification");
        }
    }

    //on ne devrait pas supprimer l'item du menu s'il existe des facture en memoire avec des item de commande correspondant.
    //A changer
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
