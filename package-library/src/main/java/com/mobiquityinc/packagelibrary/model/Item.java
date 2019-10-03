package com.mobiquityinc.packagelibrary.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    int index;
    double weight;
    double cost;
}
