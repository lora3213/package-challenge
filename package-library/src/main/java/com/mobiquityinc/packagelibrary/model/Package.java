package com.mobiquityinc.packagelibrary.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Model for Package, using lombok for doesn't has replicate code
 */
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
