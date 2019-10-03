package com.mobiquityinc.packagelibrary.model;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Enum for Characters
 */
public enum Character {

    IDENTIFIER_OF_NOT_ITEM_FOUND("-"),
    EURO_CURRENCY("€"),
    ITEM_SEPARATOR(",");

    private final String value;

    private Character(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
