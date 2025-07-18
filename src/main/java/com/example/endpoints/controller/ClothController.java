package com.example.endpoints.controller;

import com.example.model.Cloth;
import com.example.service.ClothService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloth")
@RequiredArgsConstructor
@Tag(name = "Weather", description = "Контроллер для работы с одеждой")
public class ClothController {
    private final ClothService clothService;

    @GetMapping("/all-cloth")
    public List<Cloth> createCloth(@RequestParam String cityName) {
        return clothService.allClothesForCity(cityName);
    }

    @GetMapping
    public List<Cloth> clothForCity(@RequestParam String cityName) {
        return clothService.clothesForDay(cityName);
    }

}
