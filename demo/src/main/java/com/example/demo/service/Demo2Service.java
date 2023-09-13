package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "demo2Service", url = "http://demo2:8282")
public interface Demo2Service {

    @GetMapping("/hello")
    String hello();
}
