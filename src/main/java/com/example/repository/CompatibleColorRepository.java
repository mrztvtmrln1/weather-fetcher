package com.example.repository;

import com.example.enums.ClothColors;
import com.example.model.CompatibleColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CompatibleColorRepository extends JpaRepository<CompatibleColor, Long> {
    @Query("""
    select c from CompatibleColor c
    where (c.colorOne = :color1 and c.colorTwo = :color2)
       or (c.colorOne = :color2 and c.colorTwo = :color1)
    """)
    Optional<CompatibleColor> findCompatible(
            @Param("color1") ClothColors color1,
            @Param("color2") ClothColors color2);

    @Query("""
        select distinct
            case when c.colorOne = :color then c.colorTwo else c.colorOne end
        from CompatibleColor c
        where c.colorOne = :color or c.colorTwo = :color
    """)
    List<String> findCompatibleColors(@Param("color") ClothColors color);
}
