package com.mobiquityinc.packagelibrary.service.impl;

import com.mobiquityinc.packagelibrary.model.Character;
import com.mobiquityinc.packagelibrary.model.Item;
import com.mobiquityinc.packagelibrary.model.Package;
import com.mobiquityinc.packagelibrary.service.Decision;
import org.springframework.stereotype.Service;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Service that has de domain for the decisions
 */
@Service
public class PackageDecision implements Decision {
    
    /**
     * @param aPackage
     * @return
     */
    @Override
    public String fillBestChoiceforPackages(Package aPackage){
        
        aPackage = this.discardMostWeightItems(aPackage);
        
        if (aPackage.getItem().size() == 0){
            return Character.IDENTIFIER_OF_NOT_ITEM_FOUND.getValue();
        }
        
        aPackage = this.discardElementsWithSameCost(aPackage);
        
        boolean haveBestChoice = false;
        
        while (!haveBestChoice){
            aPackage = this.discardLowAverageCostItems(aPackage);
            double sumOfWeights = 0.0;
            for (Item item :  aPackage.getItem()){
                sumOfWeights = sumOfWeights + item.getWeight();
            }
            
            if (sumOfWeights <= aPackage.getWightLimit()){
                haveBestChoice = true;
            }
        }
        return this.getIndexesOfBestChoice(aPackage);
    }
    
    /**
     * @param aPackage
     * @return
     */
    @Override
    public Package discardMostWeightItems(Package aPackage){
        for (int idx = 0; idx < aPackage.getItem().size(); idx++){
            if (aPackage.getItem().get(idx).getWeight() > aPackage.getWightLimit()){
                aPackage.getItem().remove(idx);
                idx = 0;
            }
        }
        return aPackage;
    }
    
    /**
     * @param aPackage
     * @return
     */
    @Override
    public Package discardLowAverageCostItems(Package aPackage){
        
        double averageCost = 0.0;
    
        for (Item item : aPackage.getItem()){
            averageCost = averageCost + item.getCost();
        }
        
        averageCost = averageCost / aPackage.getItem().size();
    
        for (int idx = 0; idx < aPackage.getItem().size(); idx++){
            if (aPackage.getItem().get(idx).getCost() < averageCost){
                aPackage.getItem().remove(idx);
                idx = 0;
            }
        }
        return aPackage;
    }
    
    /**
     * @param aPackage
     * @return
     */
    @Override
    public Package discardElementsWithSameCost(Package aPackage){
        
        for (int idx = 0; idx < aPackage.getItem().size(); idx ++){
            for (int idy = idx + 1 ; idy < aPackage.getItem().size(); idy++){
                if (idx == idy){
                    break;
                } else if (aPackage.getItem().get(idx).getCost() == aPackage.getItem().get(idy).getCost()){
                    if (aPackage.getItem().get(idx).getWeight() > aPackage.getItem().get(idy).getWeight()){
                        aPackage.getItem().remove(idx);
                    } else {
                        aPackage.getItem().remove(idy);
                    }
                    idx = 0;
                    idy = 0;
                }
            }
        }
        return aPackage;
    }
    
    /**
     * @return
     */
    @Override
    public String getIndexesOfBestChoice(Package aPackage){
        
        String indexesRsult = "";
        
        for (int idx= 0; idx < aPackage.getItem().size(); idx++){
            
            if (idx == aPackage.getItem().size() -1){
                indexesRsult = indexesRsult + aPackage.getItem().get(idx).getIndex();
            }else {
                indexesRsult = indexesRsult + aPackage.getItem().get(idx).getIndex()
                        + Character.ITEM_SEPARATOR.getValue();
            }
        }
        return indexesRsult;
    }
}