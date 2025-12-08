package com.example.repository;

import com.example.model.Cloth;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothRepository extends JpaRepository<Cloth, Long> {

    @Query("""
        SELECT c FROM Cloth c
        WHERE c.user.id = :userId
          AND :temp BETWEEN c.minTemp AND c.maxTemp
          AND (:wind = false OR c.isWearableInWind = true)
        """)
    List<Cloth> findByUserAndTempRangeAndWind(@Param("userId") Long userId,
                                              @Param("temp") Integer temp,
                                              @Param("wind") boolean wind, Pageable unpaged);
}
