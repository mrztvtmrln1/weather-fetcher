package com.example.endpoints.controller;

import com.example.model.Cloth;
import com.example.service.ClothService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloth")
@RequiredArgsConstructor
@Tag(name = "Weather", description = "Контроллер для работы с одеждой")
public class ClothController {
    private final ClothService clothService;

    @PostMapping
    public Cloth createCloth(@RequestBody Cloth cloth) {
        return clothService.createCloth(cloth);
    }

    @GetMapping
    public List<Cloth> clothForCity(@RequestParam String cityName) {
        return clothService.clothesForDay(cityName);
    }

}
