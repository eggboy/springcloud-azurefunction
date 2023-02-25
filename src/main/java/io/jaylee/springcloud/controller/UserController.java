package io.jaylee.springcloud.controller;

import io.jaylee.springcloud.model.UserDTO;
import io.jaylee.springcloud.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/user")
    public String createUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}