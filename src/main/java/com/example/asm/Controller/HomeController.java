package com.example.asm.Controller;

import com.example.asm.Entity.Product;
import com.example.asm.Service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private ProductService service;

    @GetMapping("/index")
    public String getIndex(HttpServletRequest request, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        int pageSize = 5; // Set the page size (number of products per page)
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Product> page = service.findAll(pageable);

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
        return "/views/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Xóa cookie
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("Xóa cookie thành công !");
        return "redirect:/login/form";
    }
}
