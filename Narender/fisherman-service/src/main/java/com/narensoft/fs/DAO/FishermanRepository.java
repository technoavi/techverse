package com.narensoft.fs.DAO;

import com.narensoft.fs.models.Fisherman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Integer> {

    List<Fisherman> findByLocation(String location);
    List<Fisherman> findByLocationIn(List<String> locations);

    @Query(value = "select * from fisher_man as fm, fish as f where fm.fisherman_id = f.fm_id and fm.fisherman_id=:id", nativeQuery = true)
    List<Fisherman> findTest(@Param("id") int id);
}
//Use-case
