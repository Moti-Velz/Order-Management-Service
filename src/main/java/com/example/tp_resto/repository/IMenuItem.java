package com.example.tp_resto.repository;

import com.example.tp_resto.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

//We don't need '@Repository' annotation because JpaRepository already carries it
public interface IMenuItem extends JpaRepository<MenuItem, Integer> {
}
