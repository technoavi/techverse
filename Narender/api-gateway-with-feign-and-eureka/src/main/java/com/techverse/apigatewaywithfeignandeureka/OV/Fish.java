package com.techverse.apigatewaywithfeignandeureka.OV;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fish {

    private Integer fishId;

    private String name;

    private String type;

    private Double weight;

    private Double price;

}
