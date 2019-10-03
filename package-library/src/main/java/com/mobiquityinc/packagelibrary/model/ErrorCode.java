package com.mobiquityinc.packagelibrary.model;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Enum for Error codes
 */
public enum ErrorCode {

    FILE_NOT_FOUND("EC-01 - File not found"),
    NUMBER_OF_ITEMS_EXCEED("EC-02 - Number of items exceed the maximumk");

    private final String value;

    private ErrorCode(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
