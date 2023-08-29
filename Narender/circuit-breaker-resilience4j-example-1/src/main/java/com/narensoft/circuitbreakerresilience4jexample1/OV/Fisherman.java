package com.narensoft.circuitbreakerresilience4jexample1.OV;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fisherman {

    private Integer fishermanId;

    private String name;

    private Long phone;

    private String location;

    private String address;

    private int fishCount;


    private List<Fish> fishList = new ArrayList<>();
}
