package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandeRepo extends JpaRepository<Commande, Integer> {

    Commande getById(int id);

    void addCommande(CommandeItem orderItem);

    void updateCommandeById(Integer id, Commande orderItem);

    void deleteCommandeById(Integer id);
}
