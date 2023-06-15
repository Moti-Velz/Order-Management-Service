package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommandeItemRepo extends JpaRepository<CommandeItem, Integer> {


    List<CommandeItem> findByCommande(Commande theCommande);
}
