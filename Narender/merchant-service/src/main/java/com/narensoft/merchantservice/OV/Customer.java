package com.narensoft.merchantservice.OV;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private int id;

    private String name;

    private long phone;

    private String location;

    private String address;
}
