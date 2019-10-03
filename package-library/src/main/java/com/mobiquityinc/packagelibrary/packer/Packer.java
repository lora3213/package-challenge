package com.mobiquityinc.packagelibrary.packer;

import com.mobiquityinc.packagelibrary.exception.APIException;
import com.mobiquityinc.packagelibrary.model.Package;
import com.mobiquityinc.packagelibrary.service.Decision;
import com.mobiquityinc.packagelibrary.service.File;
import com.mobiquityinc.packagelibrary.service.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Centralized service that call necesary methods for packer logic
 */
@Service
public class Packer {
    
    @Autowired
    private File txtFile;
    
    @Autowired
    private Transform packageTransform;
    
    @Autowired
    private Decision packageDecision;
    
    private List<Package> packages = new ArrayList<Package>();
    
    /**
     * @param filePath
     * @return
     * @throws APIException
     */
    public String pack(String filePath) throws APIException {
    
        List<String> fileLines = this.txtFile.getLFileLines(filePath);
        for (String line : fileLines){
            this.packages.add(this.packageTransform.transformLineFileToPackage(line));
        }
        
        for (Package aPackage : this.packages){
            if (!Optional.ofNullable(aPackage.getBestChoice()).isPresent()){
                aPackage.setBestChoice(this.packageDecision.fillBestChoiceforPackages(aPackage));
            }
        }
        
        String concatResults = "";
        for (Package aPackage : this.packages){
            concatResults = concatResults + aPackage.getBestChoice() + "\n";
        }
        
        return concatResults;
    }
    
}
