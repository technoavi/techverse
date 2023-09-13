package com.techverse.oneToOneuniDir.entities;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fish")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fish_id")
    private Integer fishId;

    @Column(name = "fish_name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "price")
    private Double price;

}