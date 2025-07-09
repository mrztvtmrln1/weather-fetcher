package com.example.endpoints.controller;

import com.example.dto.ColorCompatibleDto;
import com.example.model.CompatibleColor;
import com.example.service.CompatibleColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compatible-color")
@RequiredArgsConstructor
public class CompatibleColorController {
    private final CompatibleColorService compatibleColorService;

    @PostMapping
    public CompatibleColor addCompatibleColor(@RequestBody ColorCompatibleDto dto){
        return compatibleColorService.addCompatibleColor(dto);
    }
}
