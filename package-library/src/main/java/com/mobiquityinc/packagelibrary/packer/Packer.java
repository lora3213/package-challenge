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
    
    private static File txtFile;
    private static Transform packageTransform;
    private static Decision packageDecision;
    
    @Autowired
    public Packer(File txtFile, Transform packageTransform, Decision packageDecision){
        Packer.txtFile = txtFile;
        Packer.packageTransform = packageTransform;
        Packer.packageDecision = packageDecision;
    }

    /**
     * @param filePath
     * @return
     * @throws APIException
     */
    public static String pack(String filePath) throws APIException {
    
        List<Package> packages = new ArrayList<Package>();
    
        List<String> fileLines = txtFile.getLFileLines(filePath);
        for (String line : fileLines){
            packages.add(packageTransform.transformLineFileToPackage(line));
        }
        
        for (Package aPackage : packages){
            if (!Optional.ofNullable(aPackage.getBestChoice()).isPresent()){
                aPackage.setBestChoice(packageDecision.fillBestChoiceforPackages(aPackage));
            }
        }
        
        String concatResults = "";
        for (Package aPackage : packages){
            concatResults = concatResults + aPackage.getBestChoice() + "\n";
        }
        
        return concatResults;
    }
    
}
