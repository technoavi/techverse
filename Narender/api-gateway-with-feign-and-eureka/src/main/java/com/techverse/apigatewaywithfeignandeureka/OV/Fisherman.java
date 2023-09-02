package com.techverse.apigatewaywithfeignandeureka.OV;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fisherman {
    private Integer fishermanId;

    private String name;

    private Long phone;

    private String location;

    private String address;

    private String fishCount;

}
