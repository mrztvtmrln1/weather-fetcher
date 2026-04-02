package com.example.endpoints.controller;

import com.example.dto.ColorCompatibleDto;
import com.example.dto.CommonResponseDto;
import com.example.enums.ClothColors;
import com.example.model.User;
import com.example.model.UserDeactivationHistory;
import com.example.service.CompatibleColorService;
import com.example.service.UserDeactivationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/compatible-color")
@RequiredArgsConstructor
public class CompatibleColorController {
    private final CompatibleColorService compatibleColorService;
    private final UserDeactivationHistoryService userDeactivationHistoryService;

    @PostMapping
    public CommonResponseDto<ColorCompatibleDto> addCompatibleColor(@RequestBody ColorCompatibleDto dto){
        return new CommonResponseDto<>(true, compatibleColorService.addCompatibleColor(dto));
    }
    @GetMapping
    public List<String> getAllCompatibleColors(@RequestParam ClothColors color) {
        return compatibleColorService.allCompatibleColors(color);
    }
}
