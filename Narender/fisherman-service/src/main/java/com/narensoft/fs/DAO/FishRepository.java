package com.narensoft.fs.DAO;

import com.narensoft.fs.models.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {

    @Query(value = "select * from fish where fm_id=:fishermanId", nativeQuery = true)
    List<Fish> getFishByFisherman(@Param("fishermanId") int fishermanId);


}
