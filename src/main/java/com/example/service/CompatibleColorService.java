package com.example.service;

import com.example.dto.ColorCompatibleDto;
import com.example.enums.ClothColors;
import com.example.mapper.CompatibleColorMapper;
import com.example.model.CompatibleColor;
import com.example.repository.CompatibleColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompatibleColorService {
    private final CompatibleColorRepository compatibleColorRepository;
    private final CompatibleColorMapper compatibleColorMapper;

    public boolean areColorsCompatible(ClothColors color1, ClothColors color2) {
        return compatibleColorRepository.findCompatible(color1, color2).isPresent();
    }

    public CompatibleColor addCompatibleColor(ColorCompatibleDto dto) {
        CompatibleColor compatibleColor = compatibleColorMapper.toEntity(dto);
        return compatibleColorRepository.save(compatibleColor);
    }
}
