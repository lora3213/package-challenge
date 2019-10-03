package com.mobiquityinc.packagelibrary.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Package implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    double wightLimit;
    String bestChoice;
    List<Item> item;
}
