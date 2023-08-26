package com.narensoft.fs.DAO;

import com.narensoft.fs.models.Fisherman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FishermanRepository extends JpaRepository<Fisherman, Integer> {

    List<Fisherman> findByLocation(String location);
    List<Fisherman> findByLocationIn(List<String> locations);
}
