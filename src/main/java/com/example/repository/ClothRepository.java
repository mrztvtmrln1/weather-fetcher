package com.example.repository;

import com.example.model.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothRepository extends JpaRepository<Cloth,Long> {
}
