package com.example.service;

import com.example.model.Cloth;
import com.example.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClothService {
    private final ClothRepository clothRepository;

    public Cloth createCloth(Cloth cloth) {
        return clothRepository.save(cloth);
    }
}
