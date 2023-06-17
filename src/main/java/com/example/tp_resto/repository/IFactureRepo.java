package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface IFactureRepo extends JpaRepository<Facture, Integer> {
    //Verifier si cette methode marche
    boolean existsByCommandeId(Integer id);

    Optional<Facture> findFactureByCommande_Id(Integer id);


    List<Facture> findByBillTime(LocalDateTime billTime);
    }

