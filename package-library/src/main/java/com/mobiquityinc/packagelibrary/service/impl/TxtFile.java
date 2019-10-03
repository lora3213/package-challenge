package com.mobiquityinc.packagelibrary.service.impl;

import com.mobiquityinc.packagelibrary.exception.APIException;
import com.mobiquityinc.packagelibrary.service.File;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TxtFile implements File {
    
    @Override
    public List<String> getLFileLines(String filePath) throws APIException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> lines = reader.lines().collect(Collectors.toList());
            reader.close();
            return lines;
        } catch (IOException e){
            throw new APIException("File not found", e);
        }
    }
}
