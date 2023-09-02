package com.technoavi.fisheriz.ms.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Table(name = "fishes")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Fishes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "f_id")
    private Integer id;



    @Column(name = "rate")
    private Integer rate;

    @Column(name = "type")
    private String type;

    public Fishes(Integer rate, String type) {
        this.rate = rate;
        this.type = type;
    }







}