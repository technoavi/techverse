package com.narensoft.fs.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fish")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Why what happens?
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

    /*@ManyToOne
    @JoinColumn(name = "fisherman_Id")
    private Fisherman fisherMan;
    */
}
