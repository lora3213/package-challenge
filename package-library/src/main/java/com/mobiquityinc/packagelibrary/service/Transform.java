package com.mobiquityinc.packagelibrary.service;

import com.mobiquityinc.packagelibrary.exception.APIException;
import com.mobiquityinc.packagelibrary.model.Package;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Interface that has the contract for Transformations
 */
public interface Transform {
    
    /**
     * @param fileLines
     * @return
     */
    Package transformLineFileToPackage(String fileLines) throws APIException;
}
