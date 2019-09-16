package com.mostafa.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {
    @GetMapping("/testrest")
    public String test(){
        return "hi gcfhdf";
    }
}
