package com.example.secondhand.controller;


import com.example.secondhand.dto.CreateUserRequest;
import com.example.secondhand.dto.UpdateUserRequest;
import com.example.secondhand.dto.UserDto;
import com.example.secondhand.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{mail}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String mail) {
        return ResponseEntity.ok(userService.getUserByMail(mail));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @PutMapping("/{mail}")
    public ResponseEntity<Void> updateUser(@PathVariable String mail, @RequestBody UpdateUserRequest request) {
        userService.updateUser(mail, request);
        return ResponseEntity.noContent().build();
    }



}
