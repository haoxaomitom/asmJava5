package com.example.asm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Product {
    @GetMapping("/product")
    public String getIndex() {

        return "/views/product";
    }
}