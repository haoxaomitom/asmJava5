package com.example.asm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginCtrl {
    @GetMapping("/login")
    public String getIndex(){
        return "/views/login";
    }
}
