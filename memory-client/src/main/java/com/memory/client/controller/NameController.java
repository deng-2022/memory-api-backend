package com.memory.client.controller;

import com.memory.clientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 邓哈哈
 * 2023/7/28 22:52
 * Function: 获取当前用户昵称
 * Version 1.0
 */

@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET 我的名字是: " + name;
    }

    @PostMapping("/")
    public String getNameByPost(String name) {
        return "POST 我的名字是: " + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        return "POST 我的名字是: " + user.getName();
    }
}
