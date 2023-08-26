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
    @GeneratedValue(strategy = GenerationType.AUTO) //Why what happens?
    @Column(name = "ID")
    private Integer fishId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "fisherman_Id")
    private Fisherman fisherMan;
}
