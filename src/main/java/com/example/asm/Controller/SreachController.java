package com.example.asm.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SreachController {
    @GetMapping("/search")
    public String index(Model model, HttpServletRequest request , @RequestParam(name = "query", required = false) String query) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        HttpSession session = request.getSession();
        String usernamess = (String) session.getAttribute("username");
        System.out.println("Session username: "+usernamess);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        model.addAttribute("username", usernamess);
        model.addAttribute("query", query);

        return "/views/search";
    }
}
