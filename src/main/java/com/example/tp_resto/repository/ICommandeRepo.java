package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ICommandeRepo extends JpaRepository<Commande, Integer> {

}
