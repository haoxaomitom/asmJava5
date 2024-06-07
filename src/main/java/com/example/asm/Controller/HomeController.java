package com.example.asm.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index")
    public String getIndex(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        HttpSession session = request.getSession();
        String usernamess = (String) session.getAttribute("username");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        if (username != null) {
            model.addAttribute("username", usernamess);
        } else {
            return "redirect:/login/form";
        }
        model.addAttribute("username", username);
        return "/views/index";
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Xóa cookie
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/login/form";
    }
}
