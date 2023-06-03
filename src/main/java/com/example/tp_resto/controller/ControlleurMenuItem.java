package com.example.tp_resto.controller;
import com.example.tp_resto.entity.MenuItem;

import com.example.tp_resto.exception.MenuItemNotFoundException;
import com.example.tp_resto.service.MenuItemService;
import com.example.tp_resto.service.MenuItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControlleurMenuItem {

    private MenuItemService menuItemService;

    @Autowired
    public ControlleurMenuItem(MenuItemService menuItemService){
        this.menuItemService = menuItemService;
    }

    //Gestion d'exception ici ?
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
        MenuItem item = null;
        try{
            return  menuItemService.getById(id);
        }catch(MenuItemNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MenuItem Introuvable");
        }
    }

    @PostMapping("/menuitems")
    public ResponseEntity<?> addMenuItem(@RequestBody MenuItem newMenuItem) {
        MenuItem menuItem = null;
        try {
            menuItem = menuItemService.createMenuItem(newMenuItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MenuItem non créée");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem.getName().toString() + " créée");
    }

    //Faire la logique d'exception avec MenuItemNotFoundException
    @PutMapping("/menuitems/{id}")
    public ResponseEntity<?> updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem){
        MenuItem newMenuItem = menuItemService.updateMenuItemById(id, menuItem);
        if(newMenuItem == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MenuItem avec id " + id + "introuvable.");
        } else return ResponseEntity.status(HttpStatus.CREATED).body(newMenuItem.getName().toString() + "mis a jour");
    }

    //Gestion d'exception custom ici aussi
    @DeleteMapping("/menuitems/{id}")
    public ResponseEntity <String> deleteMenuItemById(@PathVariable int id) {
        boolean deleted = menuItemService.deleteMenuItemById(id);
        if(deleted){
        return ResponseEntity.ok("MenuItem avec id " + id + " supprimé");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MenuItem avec id " + id + "introuvable.");
        }
    }
}
