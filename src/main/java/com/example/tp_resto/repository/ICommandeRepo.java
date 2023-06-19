package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICommandeRepo extends JpaRepository<Commande, Integer> {


    Optional<Commande> findByFacture_Id(Integer id);
}
