package com.mobiquityinc.packagelibrary.service.impl;

import com.mobiquityinc.packagelibrary.model.Item;
import com.mobiquityinc.packagelibrary.model.Package;
import com.mobiquityinc.packagelibrary.service.Transform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Service that has de domain transformation of packages
 */
@Service
public class PackageTransform  implements Transform {
    
    @Value("${constraints.maxWeightPackage}")
    private String maxWeightPackage;
    
    @Value("${constraints.maxWeightItem}")
    private String maxWeightItem;
    
    @Value("${constraints.maxCostItem}")
    private String maxCostItem;
    
    /**
     * @param fileLines
     * @return
     */
    @Override
    public Package transformLineFileToPackage(String fileLines){
        
        List<String> itemsOfline = new ArrayList<String>();
        for (String field : fileLines.split(" ")){
            itemsOfline.add(field);
        }
        
        Package temporalPackage = new Package();
        temporalPackage.setWightLimit(Double.parseDouble(itemsOfline.get(0)));
        
        if (temporalPackage.getWightLimit() > Double.parseDouble(maxWeightPackage)){
            temporalPackage.setBestChoice("-");
        }
        
        itemsOfline.remove(0);
        itemsOfline.remove(0);
        
        List<Item> temporalItems = new ArrayList<Item>();
        
        for (String item : itemsOfline){
            Item itemPackage = new Item();
            for (String field : item.split(",")){
                if (field.contains("(")){
                    field = field.replace("(","");
                    itemPackage.setIndex(Integer.parseInt(field));
                } else if (field.contains(")")){
                    field = field.replace(")","");
                    field = field.replace("€","");
                    double cost = Double.parseDouble(field);
                    if (cost > Double.parseDouble(maxCostItem)){
                        temporalPackage.setBestChoice("-");
                    }
                    itemPackage.setCost(cost);
                } else {
                    double weight = Double.parseDouble(field);
                    if (weight > Double.parseDouble(maxWeightItem)){
                        temporalPackage.setBestChoice("-");
                    }
                    itemPackage.setWeight(weight);
                }
            }
            temporalItems.add(itemPackage);
        }
        
        temporalPackage.setItem(temporalItems);
        
        return temporalPackage;
    }
}
