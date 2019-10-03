package com.mobiquityinc.packagelibrary.service;

import com.mobiquityinc.packagelibrary.exception.APIException;

import java.util.List;


public interface File {
    
    List<String> getLFileLines(String filePath) throws APIException;

}
