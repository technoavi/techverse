package com.techverse.oneToOneuniDir.DAO;

import com.techverse.oneToOneuniDir.entities.Fisherman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Integer> {
}
