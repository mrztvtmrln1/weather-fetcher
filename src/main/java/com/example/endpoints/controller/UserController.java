package com.example.endpoints.controller;

import com.example.dto.ChangeStatusRequest;
import com.example.dto.CommonResponseDto;
import com.example.dto.UserResponseDto;
import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public CommonResponseDto<UserResponseDto> addUser(@RequestBody User user) {
        return new CommonResponseDto<>(true, userService.save(user));
    }

    @PostMapping("/change-status")
    public User changeStatus(@RequestBody ChangeStatusRequest changeStatusRequest) {
        return userService.changeStatus(changeStatusRequest.userId(), changeStatusRequest.newStatus());
    }

    @GetMapping("/user/byId")
    public CommonResponseDto<User> getUserById(@RequestParam Long id) {
        return new CommonResponseDto<>(true, userService.getUserInfoById(id));
    }

}
