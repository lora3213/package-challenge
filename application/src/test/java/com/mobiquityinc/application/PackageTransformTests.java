package com.mobiquityinc.application;

import com.mobiquityinc.application.app.Application;
import com.mobiquityinc.packagelibrary.exception.APIException;
import com.mobiquityinc.packagelibrary.model.Item;
import com.mobiquityinc.packagelibrary.model.Package;
import com.mobiquityinc.packagelibrary.service.impl.PackageTransform;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class PackageTransformTests {
    
    private PackageTransform service;
    private String correctRow;
    private String incorrectPackageWeightRow;
    private String incorrectCostWeightItemRow;
    private String incorrectWeightItemRow;
    private String incorrectQuantityOfItemsRow;
    private Package expectedResult;
    
    @Before
    public void setup(){
        ApplicationContext context = SpringApplication.run(Application.class);
        this.service = (PackageTransform) context.getBean("packageTransform");
        this.correctRow = "8 : (1,15.3,€34)";
        this.incorrectPackageWeightRow = "101 : (1,15.3,€34)";
        this.incorrectCostWeightItemRow = "8 : (1,15.3,€105)";
        this.incorrectWeightItemRow = "8 : (1,150.3,€34)";
        this.incorrectQuantityOfItemsRow = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64) (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
        this.expectedResult = new Package();
        this.expectedResult.setWightLimit(8.0);
        Item item = new Item();
        item.setIndex(1);
        item.setWeight(15.3);
        item.setCost(34.0);
        this.expectedResult.setItem(new ArrayList<Item>());
        this.expectedResult.getItem().add(item);
    }
    
    @Test
    public void getFileLinesTest() throws APIException {
        Package result = this.service.transformLineFileToPackage(this.correctRow);
        assertEquals(result, expectedResult);
    }
    
    @Test(expected = APIException.class)
    public void constraintOfLimitOfPackageWeight() throws APIException {
        this.service.transformLineFileToPackage(this.incorrectPackageWeightRow);
    }
    
    @Test(expected = APIException.class)
    public void constraintOfLimitOfCostItem() throws APIException {
        this.service.transformLineFileToPackage(this.incorrectCostWeightItemRow);
    }
    
    @Test(expected = APIException.class)
    public void constraintOfLimitOfWeightItem() throws APIException {
        this.service.transformLineFileToPackage(this.incorrectWeightItemRow);
    }
    
    @Test(expected = APIException.class)
    public void constraintOfQuantytiLimitOfItems() throws APIException {
        this.service.transformLineFileToPackage(this.incorrectQuantityOfItemsRow);
    }
}
