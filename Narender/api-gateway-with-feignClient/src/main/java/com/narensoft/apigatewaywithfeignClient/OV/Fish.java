package com.narensoft.apigatewaywithfeignClient.OV;

import lombok.*;

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
