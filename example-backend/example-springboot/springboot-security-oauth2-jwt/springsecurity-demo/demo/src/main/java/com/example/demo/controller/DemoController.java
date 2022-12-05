package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demos")
public class DemoController {
    @GetMapping("home")
    public Object home() {
        return "demos home" ;
    }


    @PostMapping("post")
    public Object post() {
        return "demos post" ;
    }
}