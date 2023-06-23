package com.example.tp_resto.exception;

/**
 * Cette classe représente une exception personnalisée qui est levée lorsqu'une facture n'est pas trouvée.
 * Elle hérite de la classe RuntimeException, ce qui signifie qu'il s'agit d'une exception non vérifiée.
 */
public class FactureNotFoundException extends RuntimeException {

    /**
     * Constructeur pour créer une nouvelle instance de FactureNotFoundException.
     * Il appelle le constructeur de la superclasse RuntimeException avec un message d'erreur spécifique.
     *
     * @param msg Le message d'erreur spécifique.
     */
    public FactureNotFoundException(String msg) {
        super(msg);
    }
}
