package com.example.tp_resto.controller;
import com.example.tp_resto.entity.MenuItem;

import com.example.tp_resto.service.MenuItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControlleurMenuItem {

    private MenuItemServiceImpl menuItemService;

    @Autowired
    public ControlleurMenuItem(MenuItemServiceImpl menuItemService){
        this.menuItemService = menuItemService;
    }


    @GetMapping("/menuitems")
    public ResponseEntity<?> getAllMenuItems(){
        List<MenuItem> List = menuItemService.getAll();
        return ResponseEntity.ok(List);
    }

    @PostMapping("/menuitems")
    public ResponseEntity<?> addCommande(@RequestBody MenuItem newMenuItem) {
        MenuItem menuItem;
        try {
            menuItem = menuItemService.createMenuItem(newMenuItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MenuItem non créée");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem.toString() + " créée");
    }

    @PutMapping("/menuitems/{id}")
    public ResponseEntity<?> updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem){


        MenuItem newMenuItem = menuItemService.updateMenuItemById(id, menuItem);
        if(newMenuItem == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MenuItem avec id " + id + "introuvable.");
        } else return ResponseEntity.status(HttpStatus.CREATED).body(newMenuItem.toString() + "mis a jour");
    }

    @DeleteMapping("/menuitems/{id}")
    public ResponseEntity <String> deleteMenuItemById(@PathVariable int id) {
        boolean deleted = menuItemService.deleteMenuItemById(id);
        if(deleted){
        return ResponseEntity.ok("MenuItem avec id " + id + " supprimé");
        } else {
            return ResponseEntity.badRequest().body("MenuItem avec id " + id + " introuvable");
        }
    }
}
