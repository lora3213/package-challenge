package com.mobiquityinc.packagelibrary.service.impl;

import com.mobiquityinc.packagelibrary.exception.APIException;
import com.mobiquityinc.packagelibrary.model.ErrorCode;
import com.mobiquityinc.packagelibrary.service.File;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Service that has de domain for the text files
 */
@Service
public class TxtFile implements File {
    
    /**
     * @param filePath
     * @return
     * @throws APIException
     */
    @Override
    public List<String> getLFileLines(String filePath) throws APIException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> lines = reader.lines().collect(Collectors.toList());
            reader.close();
            return lines;
        } catch (IOException e){
            throw new APIException(ErrorCode.FILE_NOT_FOUND.getValue(), e);
        }
    }
}
