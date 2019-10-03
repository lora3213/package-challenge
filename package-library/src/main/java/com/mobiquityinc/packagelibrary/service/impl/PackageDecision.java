package com.mobiquityinc.packagelibrary.service.impl;

import com.mobiquityinc.packagelibrary.model.Character;
import com.mobiquityinc.packagelibrary.model.Package;
import com.mobiquityinc.packagelibrary.model.ItemCombination;
import com.mobiquityinc.packagelibrary.service.Decision;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Service that has de domain for the decisions
 */
@Service
public class PackageDecision implements Decision {
    
    private List<ItemCombination> itemCombinations;
    
    
    /**
     * @param aPackage
     * @return
     */
    @Override
    public String fillBestChoiceforPackages(Package aPackage){
        
        itemCombinations = new ArrayList<ItemCombination>();
        
        for (int idx=0; idx < aPackage.getItem().size(); idx++){
            ItemCombination singleCombination = new ItemCombination();
            singleCombination.setIndexes(aPackage.getItem().get(idx).getIndex() + "");
            singleCombination.setSumWeight(aPackage.getItem().get(idx).getWeight());
            singleCombination.setSumCost(aPackage.getItem().get(idx).getCost());
            this.getBestChoiceInAllCombinations(singleCombination, aPackage);
            
            for (int idy= idx + 1; idy < aPackage.getItem().size() ;idy++){
                int previousItemCombination = itemCombinations.size() -1;
                ItemCombination itemCombination = new ItemCombination();
                
                String sumOfIndexes = itemCombinations.get(previousItemCombination).getIndexes() +
                        Character.ITEM_SEPARATOR.getValue() + aPackage.getItem().get(idy).getIndex();
                double sumOfWeights = itemCombinations.get(previousItemCombination).getSumWeight() +
                        aPackage.getItem().get(idy).getWeight();
                double sumOfCosts = itemCombinations.get(previousItemCombination).getSumCost() +
                        aPackage.getItem().get(idx).getCost();
    
                itemCombination.setIndexes(sumOfIndexes);
                itemCombination.setSumWeight(sumOfWeights);
                itemCombination.setSumCost(sumOfCosts);
    
                this.getBestChoiceInAllCombinations(itemCombination, aPackage);
            }
        }
        return this.getIndexesOfBestChoice();
    }
    
    /**
     * @param itemCombinationEvaluate
     * @param aPackage
     */
    private void getBestChoiceInAllCombinations(ItemCombination itemCombinationEvaluate, Package aPackage){
        
        if (itemCombinationEvaluate.getSumWeight() < aPackage.getWightLimit()){
            if (this.itemCombinations.size() == 0){
                itemCombinationEvaluate.setBestChoice(true);
            }
        } else {
            itemCombinationEvaluate.setBestChoice(false);
        }
        
        if (itemCombinationEvaluate.getSumWeight() < aPackage.getWightLimit() && this.itemCombinations.size() > 0){
            for (int idx = 0; idx < this.itemCombinations.size(); idx++) {
                if (itemCombinationEvaluate.getSumCost() > this.itemCombinations.get(idx).getSumCost()){
                    itemCombinationEvaluate.setBestChoice(true);
                    this.itemCombinations.get(idx).setBestChoice(false);
                }
            }
        }
        this.itemCombinations.add(itemCombinationEvaluate);
    }
    
    /**
     * @return
     */
    private String getIndexesOfBestChoice(){
        
        String indexesRsult = Character.IDENTIFIER_OF_NOT_ITEM_FOUND.getValue();
        
        for (ItemCombination itemCombinationItem : this.itemCombinations){
            if (itemCombinationItem.isBestChoice()){
                indexesRsult = itemCombinationItem.getIndexes();
                break;
            }
        }
        return indexesRsult;
    }
}