package com.mobiquityinc.packagelibrary.service.impl;

import com.mobiquityinc.packagelibrary.model.Package;
import com.mobiquityinc.packagelibrary.model.PackageCombination;
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
    
    private List<PackageCombination> packageCombinations;
    
    /**
     * @param aPackage
     * @return
     */
    @Override
    public String fillBestChoiceforPackages(Package aPackage){
        
        packageCombinations = new ArrayList<PackageCombination>();
        
        for (int idx=0; idx < aPackage.getItem().size(); idx++){
            PackageCombination aloneCombination = new PackageCombination();
            aloneCombination.setIndexes(aPackage.getItem().get(idx).getIndex() + "");
            aloneCombination.setSumWeight(aPackage.getItem().get(idx).getWeight());
            aloneCombination.setSumCost(aPackage.getItem().get(idx).getCost());
            this.compareWithAllCombinations(aloneCombination, aPackage);
            
            for (int idy= idx + 1; idy < aPackage.getItem().size() ;idy++){
                int packageCombinationBeforeItemIntern = packageCombinations.size() -1;
                PackageCombination combination = new PackageCombination();
    
                String nextIndexItem = aPackage.getItem().get(idy).getIndex() + "";
                double nextWeightItem = aPackage.getItem().get(idy).getWeight();
                double nextCostItem = aPackage.getItem().get(idx).getCost();

                String actualIndexCombination = packageCombinations.get(packageCombinationBeforeItemIntern).getIndexes();
                double actualtWeighCombination = packageCombinations.get(packageCombinationBeforeItemIntern).getSumWeight();
                double actualCostItemCombination = packageCombinations.get(packageCombinationBeforeItemIntern).getSumCost();
                
                String sumOfIndexes = actualIndexCombination + "," + nextIndexItem;
                double sumOfWeights = actualtWeighCombination + nextWeightItem;
                double sumOfCosts = actualCostItemCombination + nextCostItem;
    
                combination.setIndexes(sumOfIndexes);
                combination.setSumWeight(sumOfWeights);
                combination.setSumCost(sumOfCosts);
    
                this.compareWithAllCombinations(combination, aPackage);
            }
        }
        return this.getIndexesOfBestChoice();
    }
    
    /**
     * @param packageCombinationItem
     * @param aPackage
     */
    private void compareWithAllCombinations(PackageCombination packageCombinationItem, Package aPackage){
        
        if (packageCombinationItem.getSumWeight() < aPackage.getWightLimit()){
            if (this.packageCombinations.size() == 0){
                packageCombinationItem.setBestChoice(true);
            }
        } else {
            packageCombinationItem.setBestChoice(false);
        }
        
        if (packageCombinationItem.getSumWeight() < aPackage.getWightLimit() && this.packageCombinations.size() > 0){
            for (int idx= 0; idx < this.packageCombinations.size(); idx++) {
                if (packageCombinationItem.getSumCost() > this.packageCombinations.get(idx).getSumCost()){
                    packageCombinationItem.setBestChoice(true);
                    this.packageCombinations.get(idx).setBestChoice(false);
                }
            }
        }
        this.packageCombinations.add(packageCombinationItem);
    }
    
    /**
     * @return
     */
    private String getIndexesOfBestChoice(){
        
        String indexesRsult = "-";
        
        for (PackageCombination packageCombinationItem : this.packageCombinations){
            if (packageCombinationItem.isBestChoice()){
                indexesRsult = packageCombinationItem.getIndexes();
                break;
            }
        }
        return indexesRsult;
    }
}