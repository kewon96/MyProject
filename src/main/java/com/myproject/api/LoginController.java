package com.myproject.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @PostMapping("/login")
    public Object login(@RequestBody Map<String, Object> paramMap) {
        System.out.println(paramMap);
        return "123";
    }

}
