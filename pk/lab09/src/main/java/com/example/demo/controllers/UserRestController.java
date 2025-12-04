package com.example.demo.controllers;

import com.example.demo.Result;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest")
@Validated
public class UserRestController
{
    public UserService userService;
    public UserRestController(UserService userService)
    {
        this.userService = userService;
    }
    @PostMapping("/adduser")
    public ResponseEntity<Result> addUser(@Valid @RequestBody User user)
    {
        try
        {
            userService.AddUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(new Result());
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Result());
        }
    }
    @DeleteMapping("/deleteuser/{name}")
    public ResponseEntity<Result> deleteUser(@PathVariable String name)
    {
        try
        {
            userService.deleteByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(new Result());
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Result());
        }
    }
}
