package com.example.tp_resto.controller;

import com.example.tp_resto.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlleurRestaurant {

    private RestaurantService restaurantService;
    @Autowired
    public ControlleurRestaurant(RestaurantService restaurantService){this.restaurantService= restaurantService;}

    @GetMapping("/")
    public String sayHello(){return "Hello world";}
}
