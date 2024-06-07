package com.example.asm.Controller;

import com.example.asm.DTO.AccountDTO;
import com.example.asm.Entity.Account;
import com.example.asm.Service.Authenticate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginCtrl {
    @Autowired
    private Authenticate authenticate;
    @GetMapping("/form")
    public String getIndex(Model model){
        model.addAttribute("account",new AccountDTO());
        return "/views/login";
    }

    @PostMapping("/submit")
    public String login(@Valid @ModelAttribute("account") AccountDTO account,BindingResult BindingResult, HttpSession session, Model model, HttpServletResponse response) {
        System.out.println("start submit");
        System.out.println("Start authenticated");
        boolean isAuthenticated = authenticate.authenticate(account.getMakh(), account.getMatkhau());

        if (isAuthenticated) {
            session.setAttribute("username", account.getMakh());
            System.out.println("Set session success !");
            Cookie cookie = new Cookie("username", account.getMakh());
            cookie.setMaxAge(24 * 60 * 60); // Cookie tồn tại trong 1 ngày
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println("Set cookie success !");
            System.out.println("Login successfuly !");
            System.out.println("chuyển trang tới index");
            return "redirect:/index";
        } else {
            System.out.println("sai tên đăng nhập mật khẩu !");
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác !");
            return "/views/login";
        }
    }
}