package com.mobiquityinc.packagelibrary.model;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Enum for Error codes
 */
public enum ErrorCode {

    FILE_NOT_FOUND("EC-01 - File not found"),
    NUMBER_OF_ITEMS_EXCEED("EC-02 - Number of items exceed the maximum"),
    COST_EXCEED_LIMIT("EC-03 - Cost of one of the items exceed the limit"),
    WEIGHT_EXCEED_LIMIT("EC-04 - weight of one of the items exceed the limit"),
    WEIGHT_PACKAGE_EXCEED_LIMIT("EC-05 - weight of the package exceed the limit");

    private final String value;

    private ErrorCode(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
