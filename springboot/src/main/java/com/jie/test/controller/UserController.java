package com.jie.test.controller;

import com.jie.test.model.User;
import com.jie.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get")
    public User aa(@RequestParam(value = "id") Long id){
        return userService.getUserById(id);
    }
}
