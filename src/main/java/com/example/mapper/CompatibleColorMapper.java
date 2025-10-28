package com.example.mapper;

import com.example.dto.ColorCompatibleDto;
import com.example.model.CompatibleColor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompatibleColorMapper {
    @Mapping(target = "id", ignore = true)
    CompatibleColor toEntity(ColorCompatibleDto dto);
    ColorCompatibleDto toColorCompatibleDto(CompatibleColor entity);
}

