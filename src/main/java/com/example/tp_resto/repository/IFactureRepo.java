package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFactureRepo extends JpaRepository<Facture, Integer> {
}
