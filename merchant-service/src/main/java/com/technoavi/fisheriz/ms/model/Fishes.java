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





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fishes fishes = (Fishes) o;
        return Objects.equals(id, fishes.id) && Objects.equals(rate, fishes.rate) && Objects.equals(type, fishes.type) && Objects.equals(fisherman, fishes.fisherman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate, type, fisherman);
    }

}