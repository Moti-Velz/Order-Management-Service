package com.example.tp_resto.controller;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.service.CommandeItemService;
import com.example.tp_resto.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

/**
 * Cette classe est un contrôleur REST pour les opérations liées aux commandes.
 */
@RestController
public class ControlleurCommande {
//servir a starter la commande, add, gestion ect
    private final CommandeService commandeService;
    private final CommandeItemService itemService;

    /**
     * Constructeur de la classe ControlleurCommande.
     *
     * @param commandeService Le service de gestion des commandes.
     * @param itemService     Le service de gestion des articles de commande.
     */
    @Autowired
    public ControlleurCommande(CommandeService commandeService, CommandeItemService itemService) {
        this.commandeService = commandeService;
        this.itemService = itemService;
    }

    /**
     * Récupère toutes les commandes.
     *
     * @return ResponseEntity contenant la liste des commandes ou un message d'erreur.
     */
    @GetMapping("/commandes/getAll")
    public ResponseEntity<?> getAllCommandes() {
            List<Commande> list = commandeService.getAll();
            if( list == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Les Commandes sont Introuvables");
            }
            if (list.isEmpty()) {
                return ResponseEntity.status(200).body("Il y a Aucune commandes présentes a présenter");
            }
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
    /**
     * Récupère une commande par son ID.
     *
     * @param id L'ID de la commande à récupérer.
     * @return ResponseEntity contenant la commande trouvée ou un message d'erreur.
     */
    @GetMapping("/commandes/get/{id}")
    public ResponseEntity<?> getCommandeById(@PathVariable int id) {
        Optional<Commande> commande = null;
        try {
             commande = commandeService.getById(id);
            if (commande.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(commande);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande No " + id + " Introuvable");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il y a une erreur avec votre Requête, nous ne " +
                    "pouvons poursuivre votre demande.");
        }
    }

    /**
     * Ajoute un élément de commandeItem à une commande existante.
     *
     * @param commandeId  L'ID de la commande à laquelle ajouter l'élément.
     * @param commandeItem L'élément de menuItem à ajouter.
     * @return La commande mise à jour contenant l'élément ajouté, ou une exception en cas d'erreur.
     */
    @PostMapping("/commandes/add/{commandeId}")
    public Optional<Commande> addMenuItemToCommande(@PathVariable int commandeId, @RequestBody CommandeItem commandeItem) {

        try {
            Optional<Commande> commande = commandeService.getById(commandeId);
            if(!commande.isPresent()) {
                throw new RuntimeException("Commande Introuvable");
            }
            Commande commandePresente = commande.get();
            commandePresente.addItem(commandeItem);
            commandeService.saveCommande(commandePresente);

            return commandeService.getById(commandeId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un problème est survenu lors de l'ajout " +
                    "des items.");
        }
    }

    /**
     * Crée une nouvelle commande avec une liste d'éléments de commande.
     *
     * @param listeCommandeItem La liste des éléments de menuItem à ajouter.
     * @return La nouvelle commande créée avec les éléments ajoutés, ou une exception en cas d'erreur.
     */
    @PostMapping("/commandes/add")
    public Optional<Commande> createCommandeWithItems(@RequestBody List<CommandeItem> listeCommandeItem) {

        try {

        Commande commande = new Commande();

        for(CommandeItem item : listeCommandeItem) {
            commande.addItem(item);
        }
        Commande savedCommande = commandeService.saveCommande(commande);

        return commandeService.getById(savedCommande.getId());
        } catch ( Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un problème est survenu lors de la création " +
                    "de la commande");
        }
    }

    /**
     * Met à jour une commande existante.
     *
     * @param id L'ID de la commande à mettre à jour.
     * @param updatedCommande La commande mise à jour avec les nouveaux parametre fournis par l'utilisateur.
     * @return ResponseEntity contenant la commande mise à jour ou un message d'erreur.
     */
    @PutMapping("/commandes/update/{id}")
    public ResponseEntity<?> updateCommandeById(@PathVariable int id, @RequestBody Commande updatedCommande) {
        try {
            if(updatedCommande == null || updatedCommande.getOrderTime() == null || updatedCommande.getOrderItems()
                    .isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tous les champs sont obligatoires pour " +
                        "poursuivre a la modification");
            }else {
                Commande commande = commandeService.updateCommandeById2(id, updatedCommande);
                return ResponseEntity.ok(commande);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande non trouvée, impossible de la modifier.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Une erreur est subvenue, la Commande ne sera pas " +
                    "Modifiée.");
        }
        }

    /**
     * Supprime une commande par son ID.
     *
     * @param id L'ID de la commande à supprimer.
     * @return ResponseEntity indiquant le succès ou l'échec de la suppression.
     */
    @DeleteMapping("/commandes/del/{id}")
    public ResponseEntity<?> deleteCommandeById(@PathVariable int id) {
        try {
            boolean isDeleted = commandeService.deleteCommandeById(id);
            if (isDeleted) {
                return ResponseEntity.ok("Commande " + id + " à été supprimé avec succès !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Commande Non Supprimée");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un problème est survenu lors de la supression de " +
                "la commande No " + id);
    }
}

