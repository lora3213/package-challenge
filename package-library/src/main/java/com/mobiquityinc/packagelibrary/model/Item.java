package com.mobiquityinc.packagelibrary.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * @author Diego Aguirre
 * @since 03/10/2019
 *
 * Model for item of the package, using lombok for doesn't has replicate code
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
public class Item implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    int index;
    double weight;
    double cost;
}
