package com.narensoft.fs.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
//@Setter
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

    @Transient //make the field optional
    private String fishCount;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fm_id", referencedColumnName = "fisherman_id")
    private List<Fish> fishList = new ArrayList<>();

    public void setFishermanId(Integer fishermanId) {
        this.fishermanId = fishermanId;
    }

    public Fisherman setName(String name) {
        this.name = name;
        return this;
    }

    public Fisherman setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public Fisherman setLocation(String location) {
        this.location = location;
        return this;
    }

    public Fisherman setAddress(String address) {
        this.address = address;
        return this;
    }

    public Fisherman setFishList(List<Fish> fishList) {
        this.fishList = fishList;
        return this;
    }
}
