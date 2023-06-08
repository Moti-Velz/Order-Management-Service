package com.example.tp_resto.exception;

public class FactureNotFoundException extends RuntimeException {
    public FactureNotFoundException(String msg) {
        super(msg);
    }
}
