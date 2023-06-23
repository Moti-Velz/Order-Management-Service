package com.example.tp_resto.exception;

/**
 * Cette classe représente une exception personnalisée qui est levée lorsqu'un MenuItem n'est pas trouvé.
 * Elle hérite de la classe RuntimeException, ce qui signifie qu'il s'agit d'une exception non vérifiée.
 */
public class MenuItemNotFoundException extends RuntimeException
{
    /**
     * Constructeur pour créer une nouvelle instance de MenuItemNotFoundException.
     * Il appelle le constructeur de la superclasse RuntimeException avec un message d'erreur spécifique.
     *
     * @param msg Le message d'erreur spécifique.
     */
    public MenuItemNotFoundException(String msg){
        super(msg);
    }
}
