package com.example.asm.Controller;

import com.example.asm.DTO.AccountDTO;
import com.example.asm.Service.Authenticate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginCtrl {

    @Autowired
    private Authenticate authenticate;

    @GetMapping("/form")
    public String getIndex(Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("account", new AccountDTO());
        return "/views/login";
    }

    @PostMapping("/submit")
    public String login(@Valid @ModelAttribute("account") AccountDTO account, BindingResult bindingResult,
                        HttpSession session, Model model, HttpServletResponse response,
                        @RequestParam(value = "remember", required = false) String remember) {
        System.out.println("start submit");
        System.out.println("Start authenticated");
        boolean isAuthenticated = authenticate.authenticate(account.getMakh(), account.getMatkhau());

        if (isAuthenticated) {
            session.setAttribute("username", account.getMakh());
            System.out.println("Session name: " + session.getAttribute("username"));
            System.out.println("Set session success !");
            Cookie cookie = new Cookie("username", account.getMakh());
            if (remember != null && remember.equals("true")) {
                cookie.setMaxAge(24 * 60 * 60); // Cookie exists for 1 day
            } else {
                cookie.setMaxAge(-1); // Cookie lasts only for the session
            }
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println("Set cookie success !");
            System.out.println("Login successfully !");
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác !");
            return "/views/login";
        }
    }
}
