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

        String username = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        if (username == null) {
            HttpSession session = request.getSession();
            username = (String) session.getAttribute("username");
        }

        model.addAttribute("username", username);
        model.addAttribute("products", page.getContent()); // Ensure the products are added to the model
        model.addAttribute("currentPage", pageNo); // Add current page to model
        model.addAttribute("totalPages", page.getTotalPages()); // Add total pages to model

        return "/views/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Delete cookie
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("Xóa cookie thành công !");
        return "redirect:/login/form";
    }
}
