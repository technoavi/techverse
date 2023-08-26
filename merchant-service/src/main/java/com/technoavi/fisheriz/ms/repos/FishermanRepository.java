package com.technoavi.fisheriz.ms.repos;

import java.util.List;

import com.technoavi.fisheriz.ms.model.Fisherman;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FishermanRepository extends JpaRepository<Fisherman, Integer> {
  List<Fisherman> findByName(String name);
}