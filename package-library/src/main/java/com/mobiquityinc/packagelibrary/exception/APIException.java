package com.mobiquityinc.packagelibrary.exception;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Centralized Exception for package-library
 */
public class APIException extends Exception {
    
    /**
     * @param message
     * @param e
     */
    public APIException(String message, Exception e) {
        super(message, e);
    }
    
    /**
     * @param message
     */
    public APIException(String message) {
        super(message);
    }
}
