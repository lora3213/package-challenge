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

@Service
public class Packer {
    
    @Autowired
    private File txtFile;
    
    @Autowired
    private Transform packageTransform;
    
    @Autowired
    private Decision packageDecision;
    
    private List<Package> packages = new ArrayList<Package>();
    
    public String pack(String filePath) throws APIException {
    
        List<String> fileLines = this.txtFile.getLFileLines(filePath);
        for (String line : fileLines){
            this.packages.add(this.packageTransform.transformLineFileToPackage(line));
        }
        
        for (Package aPackage : this.packages){
            aPackage.setBestChoice(this.packageDecision.fillBestChoiceforPackages(aPackage));
        }
        
        String concatResults = "";
        for (Package aPackage : this.packages){
            concatResults = concatResults + aPackage.getBestChoice() + "\n";
        }
        
        return concatResults;
    }
    
}
