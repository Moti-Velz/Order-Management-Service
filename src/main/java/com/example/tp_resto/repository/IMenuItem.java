package com.example.tp_resto.repository;

import com.example.tp_resto.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//We don't need '@Repository' annotation because JpaRepository already carries it
public interface IMenuItem extends JpaRepository<MenuItem, Integer> {

    MenuItem findByNameIgnoreCase(String name);
    Optional <MenuItem> findMenuItemByName(String name);

}
