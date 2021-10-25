package br.com.hotmart.java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/department")
public class HelloController {

    @GetMapping("/")
    public String Hello() {
        return "Hello World";
    }
}
