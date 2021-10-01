package com.angelia.demo.property.model.ura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Transaction {

    private String contractDate;
    private String area;
    private String price;
    private String propertyType;
    private String typeOfArea;
    private String tenure;
    private String floorRange;
    private String typeOfSale;
    private String district;
    private String noOfUnits;
    private String nettPrice;

}
