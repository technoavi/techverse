package com.technoavi.fisheriz.ms.repos;

import com.technoavi.fisheriz.ms.model.Fishes;


import org.springframework.data.jpa.repository.JpaRepository;



public interface FishRepository extends JpaRepository<Fishes, Integer> {

}