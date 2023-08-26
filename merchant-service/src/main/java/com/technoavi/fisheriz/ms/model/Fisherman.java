package com.technoavi.fisheriz.ms.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity

@Table(name = "fisherman")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Fisherman implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "fm_id")
    private Integer fm_id;

    @Column(name = "name")
    private String name;
    private Integer salary;
    private String location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fm_id", referencedColumnName = "fm_id")
    private List<Fishes> fishes;
//@OneToMany(
//        mappedBy = "fisherman",
//        cascade = CascadeType.ALL
//)
//private List<Fishes> fishes = new ArrayList<>();

    public List<Fishes> getFishes() {
        return fishes;
    }

    public void setFishes(List<Fishes> fishes) {
        this.fishes = fishes;
    }




    public Fisherman(String name, Integer salary, String location) {
        this.name = name;
        this.salary = salary;
        this.location = location;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fisherman fisherman = (Fisherman) o;
        return Objects.equals(fm_id, fisherman.fm_id) && Objects.equals(name, fisherman.name) && Objects.equals(salary, fisherman.salary) && Objects.equals(location, fisherman.location) && Objects.equals(fishes, fisherman.fishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fm_id, name, salary, location, fishes);
    }


}