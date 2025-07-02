package com.example.service;

import com.example.repository.CompatibleColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompatibleColorService {
    private final CompatibleColorRepository compatibleColorRepository;

    public boolean areColorsCompatible(String color1, String color2) {
        return compatibleColorRepository.findCompatible(color1, color2).isPresent();
    }
}
