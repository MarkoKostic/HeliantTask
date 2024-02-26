package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@RestController
@RequestMapping("hello")
public class TestController {

    @GetMapping("user")
    public String helloUser() {
        return "Hello mr Mark!";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello admin";
    }

}
