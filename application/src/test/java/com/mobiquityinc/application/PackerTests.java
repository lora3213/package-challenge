package com.mobiquityinc.application;

import com.mobiquityinc.application.app.Application;
import com.mobiquityinc.packagelibrary.exception.APIException;
import com.mobiquityinc.packagelibrary.packer.Packer;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class PackerTests {
    
    private Packer service;
    private String filePath;
    private String resultExpected;
    
    @Before
    public void setup(){
        ApplicationContext context = SpringApplication.run(Application.class);
        this.service = (Packer) context.getBean("packer");
        this.filePath = "C:\\GitHub-lora3213\\package-challenge\\application\\src\\test\\resources\\test-file-test1.txt";
        this.resultExpected = "4\n-\n2,3,4\n8,9\n";
    }
    
    @Test
    public void packerIntegrationTest() throws APIException {
        String result = this.service.pack(this.filePath);
        assertEquals(result, resultExpected);
    }
}