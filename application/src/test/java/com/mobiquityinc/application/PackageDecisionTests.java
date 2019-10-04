package com.mobiquityinc.application;

import com.mobiquityinc.application.app.Application;
import com.mobiquityinc.packagelibrary.model.Character;
import com.mobiquityinc.packagelibrary.model.Item;
import com.mobiquityinc.packagelibrary.model.Package;
import com.mobiquityinc.packagelibrary.service.impl.PackageDecision;
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
public class PackageDecisionTests {
    
    private PackageDecision service;
    private Package packageReturnNothing;
    private Package packageReturnResult;
    private String  expectedResult;
    private Package packageExpectedForWeightElimination;
    private Package packageExpectedForAverageCostElimination;
    private Package packageExpectedForItemsWithEqualCostElimination;
    private String expectedIndexesBestChoice;
    
    @Before
    public void setup(){
        ApplicationContext context = SpringApplication.run(Application.class);
        this.service = (PackageDecision) context.getBean("packageDecision");
        
        this.packageReturnNothing = new Package();
        this.packageReturnNothing.setWightLimit(8.0);
        Item item = new Item();
        item.setIndex(1);
        item.setWeight(15.3);
        item.setCost(34.0);
        this.packageReturnNothing.setItem(new ArrayList<Item>());
        this.packageReturnNothing.getItem().add(item);
        
        this.expectedResult = "4";
        this.packageReturnResult = new Package();
        this.packageReturnResult.setWightLimit(81.0);
        item = new Item();
        item.setIndex(1);
        item.setWeight(53.38);
        item.setCost(45.0);
        this.packageReturnResult.setItem(new ArrayList<Item>());
        this.packageReturnResult.getItem().add(item);
        item = new Item();
        item.setIndex(2);
        item.setWeight(88.62);
        item.setCost(98.0);
        this.packageReturnResult.getItem().add(item);
        item = new Item();
        item.setIndex(3);
        item.setWeight(78.48);
        item.setCost(76.0);
        this.packageReturnResult.getItem().add(item);
        item = new Item();
        item.setIndex(4);
        item.setWeight(72.30);
        item.setCost(76.0);
        this.packageReturnResult.getItem().add(item);
        item = new Item();
        item.setIndex(5);
        item.setWeight(30.18);
        item.setCost(9.0);
        this.packageReturnResult.getItem().add(item);
        item = new Item();
        item.setIndex(6);
        item.setWeight(46.34);
        item.setCost(48.0);
        this.packageReturnResult.getItem().add(item);
    
        this.packageExpectedForWeightElimination = new Package();
        this.packageExpectedForWeightElimination.setWightLimit(81.0);
        item = new Item();
        item.setIndex(1);
        item.setWeight(53.38);
        item.setCost(45.0);
        this.packageExpectedForWeightElimination.setItem(new ArrayList<Item>());
        this.packageExpectedForWeightElimination.getItem().add(item);
        item = new Item();
        item.setIndex(3);
        item.setWeight(78.48);
        item.setCost(76.0);
        this.packageExpectedForWeightElimination.getItem().add(item);
        item = new Item();
        item.setIndex(4);
        item.setWeight(72.30);
        item.setCost(76.0);
        this.packageExpectedForWeightElimination.getItem().add(item);
        item = new Item();
        item.setIndex(5);
        item.setWeight(30.18);
        item.setCost(9.0);
        this.packageExpectedForWeightElimination.getItem().add(item);
        item = new Item();
        item.setIndex(6);
        item.setWeight(46.34);
        item.setCost(48.0);
        this.packageExpectedForWeightElimination.getItem().add(item);
    
        this.packageExpectedForAverageCostElimination = new Package();
        this.packageExpectedForAverageCostElimination.setWightLimit(81.0);
        item = new Item();
        item.setIndex(2);
        item.setWeight(88.62);
        item.setCost(98.0);
        this.packageExpectedForAverageCostElimination.setItem(new ArrayList<Item>());
        this.packageExpectedForAverageCostElimination.getItem().add(item);
        item = new Item();
        item.setIndex(3);
        item.setWeight(78.48);
        item.setCost(76.0);
        this.packageExpectedForAverageCostElimination.getItem().add(item);
        item = new Item();
        item.setIndex(4);
        item.setWeight(72.30);
        item.setCost(76.0);
        this.packageExpectedForAverageCostElimination.getItem().add(item);
    
        this.packageExpectedForItemsWithEqualCostElimination = new Package();
        this.packageExpectedForItemsWithEqualCostElimination.setWightLimit(81.0);
        item = new Item();
        item.setIndex(1);
        item.setWeight(53.38);
        item.setCost(45.0);
        this.packageExpectedForItemsWithEqualCostElimination.setItem(new ArrayList<Item>());
        this.packageExpectedForItemsWithEqualCostElimination.getItem().add(item);
        item = new Item();
        item.setIndex(2);
        item.setWeight(88.62);
        item.setCost(98.0);
        this.packageExpectedForItemsWithEqualCostElimination.getItem().add(item);
        item = new Item();
        item.setIndex(4);
        item.setWeight(72.30);
        item.setCost(76.0);
        this.packageExpectedForItemsWithEqualCostElimination.getItem().add(item);
        item = new Item();
        item.setIndex(5);
        item.setWeight(30.18);
        item.setCost(9.0);
        this.packageExpectedForItemsWithEqualCostElimination.getItem().add(item);
        item = new Item();
        item.setIndex(6);
        item.setWeight(46.34);
        item.setCost(48.0);
        this.packageExpectedForItemsWithEqualCostElimination.getItem().add(item);
        
        this.expectedIndexesBestChoice = "1,2,3,4,5,6";
    }
    
    @Test
    public void fillBestChoiceforPackagesReturnsNothing(){
        String result = this.service.fillBestChoiceforPackages(this.packageReturnNothing);
        assertEquals(result, Character.IDENTIFIER_OF_NOT_ITEM_FOUND.getValue());
    }
    
    @Test
    public void fillBestChoiceforPackages(){
        String result = this.service.fillBestChoiceforPackages(this.packageReturnResult);
        assertEquals(result, this.expectedResult);
    }
    
    @Test
    public void discardMostWeightItems(){
        Package result = this.service.discardMostWeightItems(this.packageReturnResult);
        assertEquals(result,this.packageExpectedForWeightElimination);
    }
    
    @Test
    public void discardLowAverageCostItems(){
        Package result = this.service.discardLowAverageCostItems(this.packageReturnResult);
        assertEquals(result,this.packageExpectedForAverageCostElimination);
    }
    
    @Test
    public void discardElementsWithSameCost(){
        Package result = this.service.discardElementsWithSameCost(this.packageReturnResult);
        assertEquals(result,this.packageExpectedForItemsWithEqualCostElimination);
    }
    
    @Test
    public void getIndexesOfBestChoice(){
        String result = this.service.getIndexesOfBestChoice(this.packageReturnResult);
        assertEquals(result,this.expectedIndexesBestChoice);
    }
}
