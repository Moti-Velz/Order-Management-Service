package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.Restaurant;
import com.example.tp_resto.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return this.restaurantRepository.findAll();
    }
}
