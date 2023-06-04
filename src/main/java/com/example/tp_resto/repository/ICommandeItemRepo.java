package com.example.tp_resto.repository;

import com.example.tp_resto.entity.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandeItemRepo extends JpaRepository<CommandeItem, Integer> {


}
