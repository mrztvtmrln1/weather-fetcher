package com.example.endpoints.controller;

import com.example.dto.CommonResponseDto;
import com.example.model.Cloth;
import com.example.service.ClothService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloth")
@RequiredArgsConstructor
@Tag(name = "Weather", description = "Контроллер для работы с одеждой")
public class ClothController {
    private final ClothService clothService;

    @GetMapping("/all-cloth")
    public List<Cloth> getAllClothes(
            @RequestParam String cityName,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return clothService.allClothesForCity(cityName,userId, pageable);
    }

    @GetMapping
    public CommonResponseDto<List<Cloth>> clothForCity(@RequestParam String cityName, @RequestParam Long baseClothId, @RequestParam Long userId) {
        return new CommonResponseDto<>(true, clothService.clothesForDay(cityName, baseClothId, userId));
    }
    @PostMapping
    public Cloth addCloth(@RequestBody Cloth cloth) {
        return clothService.save(cloth);
    }
}
