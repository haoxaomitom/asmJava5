package com.example.asm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterCtrl {
    @GetMapping("/register")
    public String getIndex(){
        return "/views/register";
    }
}
