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
    
    /**
     * @param aPackage
     * @return
     */
    Package discardMostWeightItems(Package aPackage);
    
    /**
     *
     * @param aPackage
     * @return
     */
    Package discardLowAverageCostItems(Package aPackage);
    
    /**
     * @param aPackage
     * @return
     */
    Package discardElementsWithSameCost(Package aPackage);
    
    /**
     * @param aPackage
     * @return
     */
    String getIndexesOfBestChoice(Package aPackage);
    
}
