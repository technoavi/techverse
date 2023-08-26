package com.narensoft.fs.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fisher_man")
public class Fisherman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Why what happens?
    @Column(name = "ID")
    private Integer fishermanId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "fisherMan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Fish> fishList = new ArrayList<>();
    /*
    * FetchType.LAZY - It will load the fish data Lazily i.e when fishes data requested via get method.
    * FetchType.EAGER - It will load the fish data eagerly i.e whenever FisherMan data is requested, corresponding
     fishes data will also get loaded.
    * */
}
