package com.example.tp_resto.repository;

import com.example.tp_resto.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IFactureRepo extends JpaRepository<Facture, Integer> {
    boolean existsByCommandeId(Integer id);

//        @Query(value = "SELECT f FROM Facture f WHERE DAY(f.billTime) = :day AND MONTH(f.billTime) = :month AND YEAR(f.billTime) = :year")
//        List<Facture> findByBillTime(LocalDate date);
    }

