package com.narensoft.apigatewaywithfeignClient.OV;

import lombok.*;



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
