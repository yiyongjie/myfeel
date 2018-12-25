package com.jie.test.controller;

import com.jie.test.model.User;
import com.jie.test.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Api(value = "test",description = "测试")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get")
    public User getUserById(@RequestParam(value = "id") Long id){
        return userService.getUserById(id);
    }
}
