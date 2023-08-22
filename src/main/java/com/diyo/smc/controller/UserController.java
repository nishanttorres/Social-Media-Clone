package com.diyo.smc.controller;

import com.diyo.smc.entity.User;
import com.diyo.smc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUser(){
        return ResponseEntity.ok(userService.findAllUser());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId")Long userId){
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }
    @PatchMapping
    public ResponseEntity<String> editUser(@RequestBody User user){
        return ResponseEntity.ok(userService.editUser(user));
    }
    @PostMapping("/validate")
    public ResponseEntity<User> validateLogin(@RequestBody User user){
        return ResponseEntity.ok(userService.validateUser(user));
    }
}
