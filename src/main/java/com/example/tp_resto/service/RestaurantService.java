package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.Menu;
import com.example.tp_resto.entity.Reservation;
import com.example.tp_resto.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RestaurantService {

    List<Restaurant> findAll();
}
