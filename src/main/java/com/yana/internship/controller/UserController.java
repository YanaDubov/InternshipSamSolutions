package com.yana.internship.controller;

import com.yana.internship.entity.User;
import com.yana.internship.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserAccount(@RequestBody @Valid User user){
        userService.create(user);
        userService.autoLogin(user.getEmail(),user.getPassword());
    }

}
