package com.mobiquityinc.packagelibrary.service;

import com.mobiquityinc.packagelibrary.exception.APIException;

import java.util.List;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Interface that has the contract for Files
 */
public interface File {

    /**
     * @param filePath
     * @return
     * @throws APIException
     */
    List<String> getLFileLines(String filePath) throws APIException;

}
