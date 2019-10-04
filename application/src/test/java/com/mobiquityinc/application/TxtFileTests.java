package com.mobiquityinc.application;

import com.mobiquityinc.application.app.Application;
import com.mobiquityinc.packagelibrary.exception.APIException;
import com.mobiquityinc.packagelibrary.service.impl.TxtFile;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class TxtFileTests {
    
    private TxtFile service;
    private String filePath;
    private String wrongFilePath;
    private List<String> resultExpected;
    
    @Before
    public void setup(){
        ApplicationContext context = SpringApplication.run(Application.class);
        this.service = (TxtFile) context.getBean("txtFile");
        this.filePath = "C:\\GitHub-lora3213\\package-challenge\\application\\src\\test\\resources\\test-file-test1.txt";
        this.wrongFilePath = "C:\\GitHub-lora3213\\package-challenge\\application\\src\\test\\resources\\test-file-test-wrong.txt";
        
        resultExpected = new ArrayList<String>();
        resultExpected.add("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        resultExpected.add("8 : (1,15.3,€34)");
        resultExpected.add("75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)");
        resultExpected.add("56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)");
    }
    
    @Test
    public void getFileLinesTest() throws APIException {
        List<String> result = this.service.getLFileLines(this.filePath);
        assertEquals(result, resultExpected);
    }
    
    @Test(expected = APIException.class)
    public void getLFileLinesTestException() throws APIException {
        this.service.getLFileLines(this.wrongFilePath);
    }
}
