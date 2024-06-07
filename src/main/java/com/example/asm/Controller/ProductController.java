package com.example.asm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/list")
    public String getIndex() {
        System.out.println("a");
        return "/views/index";
    }
    @PostMapping("/detail/*")
    public String detail(){
        return "views/detail";
    }
}