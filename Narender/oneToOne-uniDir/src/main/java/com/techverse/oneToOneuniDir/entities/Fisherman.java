package com.techverse.oneToOneuniDir.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fisher_man")
public class Fisherman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Why what happens?
    @Column(name = "fisherman_id")
    private Integer fishermanId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "location")
    private String location;

    @Column(name = "address")
    private String address;

    //@Transient annotation make the field optional basically the field will not take part in serialization
    @Transient
    private String fishCount;

    @OneToOne
    @JoinColumn(name = "fish_id_fk", referencedColumnName = "fish_id")
    private Fish fish;
}