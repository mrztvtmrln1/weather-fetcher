package com.example.service;

import com.example.dto.ColorCompatibleDto;
import com.example.enums.ClothColors;
import com.example.exceptions.AlreadyExistsException;
import com.example.mapper.CompatibleColorMapper;
import com.example.repository.CompatibleColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompatibleColorService {
    private final CompatibleColorRepository compatibleColorRepository;
    private final CompatibleColorMapper compatibleColorMapper;

    public boolean areColorsCompatible(ClothColors color1, ClothColors color2) {
        return compatibleColorRepository.findCompatible(color1, color2).isPresent();
    }

    public List<String> allCompatibleColors(ClothColors color) {
        return compatibleColorRepository.findCompatibleColors(color);
    }

    @Transactional
    public ColorCompatibleDto addCompatibleColor(ColorCompatibleDto dto) {
        var c1 = dto.colorOne();
        var c2 = dto.colorTwo();

        if (c1 == c2) {
            throw new IllegalArgumentException("Нельзя добавлять совместимость одного и того же цвета");
        }

        dto = normalize(dto);

        if (compatibleColorRepository.findCompatible(dto.colorOne(), dto.colorTwo()).isPresent()) {
            throw new AlreadyExistsException("Совместимость %s–%s уже существует".formatted(dto.colorOne(), dto.colorTwo()));
        }

        try {
            var saved = compatibleColorRepository.save(compatibleColorMapper.toEntity(dto));
            return compatibleColorMapper.toColorCompatibleDto(saved);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("Такая совместимость уже существует", e);
        }
    }


    private ColorCompatibleDto normalize(ColorCompatibleDto dto) {
        var a = dto.colorOne();
        var b = dto.colorTwo();
        return (a.ordinal() > b.ordinal()) ? new ColorCompatibleDto(b, a) : dto;
    }
}
