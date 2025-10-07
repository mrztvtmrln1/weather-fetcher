package com.example.mapper;

import com.example.dto.ColorCompatibleDto;
import com.example.model.CompatibleColor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompatibleColorMapper {
    CompatibleColor toEntity(ColorCompatibleDto dto);
    ColorCompatibleDto toColorCompatibleDto(CompatibleColor dto);
}
