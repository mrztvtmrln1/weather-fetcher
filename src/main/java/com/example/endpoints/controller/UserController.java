package com.example.endpoints.controller;

import com.example.dto.ChangeStatusRequest;
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
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/change-status")
    public User changeStatus(@RequestBody ChangeStatusRequest changeStatusRequest) {
        return userService.changeStatus(changeStatusRequest.userId(), changeStatusRequest.newStatus());
    }
}
