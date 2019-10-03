package com.mobiquityinc.packagelibrary.service;

import com.mobiquityinc.packagelibrary.model.Package;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Interface that has the contract for Decisions
 */
public interface Decision {
    
    /**
     * @param aPackage
     * @return
     */
    String fillBestChoiceforPackages(Package aPackage);
    
}
