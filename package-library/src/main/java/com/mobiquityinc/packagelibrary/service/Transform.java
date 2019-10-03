package com.mobiquityinc.packagelibrary.service;

import com.mobiquityinc.packagelibrary.model.Package;

public interface Transform {
    
    Package transformLineFileToPackage(String fileLines);
}
