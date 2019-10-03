package com.mobiquityinc.packagelibrary.service.impl;

import com.mobiquityinc.packagelibrary.model.Package;
import com.mobiquityinc.packagelibrary.service.Decision;
import org.springframework.stereotype.Service;

@Service
public class PackageDecision implements Decision {
    
    @Override
    public void fillBestChoiceforPackages(Package aPackage){
    
        /* Permutation logic for all possibles combinations*/
        /* Also the search for the best combination regarding to the criteria*/
        
    }
}