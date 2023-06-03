package com.example.tp_resto.exception;

public class MenuItemNotFoundException extends RuntimeException
{
    public MenuItemNotFoundException(String msg){
        super(msg);
    }
}
