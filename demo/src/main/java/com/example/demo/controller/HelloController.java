package com.example.demo.controller;

import com.example.demo.service.Demo2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @Autowired
    private Demo2Service demo2Service;

    @GetMapping
    public String hello() {
        log.debug("call demo2's hello");
        return demo2Service.hello();
    }
}
