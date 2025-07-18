package com.example.mapper;

import com.example.dto.ColorCompatibleDto;
import com.example.model.CompatibleColor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompatibleColorMapper {
    CompatibleColorMapper INSTANCE = Mappers.getMapper(CompatibleColorMapper.class);
    CompatibleColor toEntity(ColorCompatibleDto dto);
}
