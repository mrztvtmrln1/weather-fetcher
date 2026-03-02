package com.example.service.mapper;

import com.example.dto.ColorCompatibleDto;
import com.example.enums.ClothColors;
import com.example.mapper.CompatibleColorMapper;
import com.example.model.CompatibleColor;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CompatibleColorMapperTest {

    private final CompatibleColorMapper mapper =
            Mappers.getMapper(CompatibleColorMapper.class);

    @Test
    void toEntity_mapsRecordFields_andDoesNotSetId() {
        ColorCompatibleDto dto = new ColorCompatibleDto(ClothColors.BLACK, ClothColors.WHITE);

        CompatibleColor entity = mapper.toEntity(dto);

        assertNotNull(entity);
        assertNull(entity.getId(), "id должен быть null, т.к. его нет в DTO и он генерируется БД");
        assertEquals(ClothColors.BLACK, entity.getColorOne());
        assertEquals(ClothColors.WHITE, entity.getColorTwo());
    }


    @Test
    void toDto_mapsEntityFields() {
        // given
        CompatibleColor entity = new CompatibleColor();
        entity.setId(10L);
        entity.setColorOne(ClothColors.OCEAN_BLUE);
        entity.setColorTwo(ClothColors.LIME);

        // when
        ColorCompatibleDto dto = mapper.toColorCompatibleDto(entity);

        // then
        assertNotNull(dto);
        assertEquals(ClothColors.OCEAN_BLUE, dto.colorOne());
        assertEquals(ClothColors.LIME, dto.colorTwo());
    }
}

