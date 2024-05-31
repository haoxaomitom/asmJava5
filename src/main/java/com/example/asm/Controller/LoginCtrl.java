package com.example.asm.Controller;

import com.example.asm.Entity.Account;
import com.example.asm.Service.Authenticate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Console;

@Controller
public class LoginCtrl {

    @Autowired
    private Authenticate authenticateService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("account", new Account());
        System.out.println("start login");
        return "/views/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("start submit");
//        if (bindingResult.hasErrors()) {
//            System.out.println("start blind");
//            return "/views/login";
//        }
        System.out.println("Start authenticated");
        boolean isAuthenticated = authenticateService.authenticate(account.getMakh(), account.getMatkhau());

        if (isAuthenticated) {
            System.out.println("chuyển trang tới index");
            System.out.println("Login successfuly !");
            return "redirect:/index";
        } else {
            System.out.println("sai tên đăng nhập mật khẩu !");
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác !");
            return "/views/login";
        }
    }

}