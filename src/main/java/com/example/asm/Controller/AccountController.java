package com.example.asm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    @RequestMapping("/acc/login")
    public String login(){
        return "/views/login";
    }
    @RequestMapping("/acc/regiter")
    public String regiter(){
        return "/views/regiter";
    }
}
