package com.collabit.userservice.controller;

import com.collabit.userservice.entity.User;
import com.collabit.userservice.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public User save(@RequestBody User user){
        log.info("Creating(POST) new user: " + user);
        return userService.save(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserAndDepartment(@PathVariable Long userId){
        
        return ResponseEntity.ok().body(userService.getUserAndDepartment(userId));
    }
    
}
