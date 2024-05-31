package com.example.asm.Controller;

import com.example.asm.DTO.AccountDTO;
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
    public String login(@Valid @ModelAttribute("account") AccountDTO account, BindingResult bindingResult, Model model) {
        System.out.println("start submit");
        System.out.println("Start authenticated");
        boolean isAuthenticated = authenticate.authenticate(account.getMakh(), account.getMatkhau());

        if (isAuthenticated && !bindingResult.hasErrors()) {
            System.out.println("chuyển trang tới index");
            System.out.println("Login successfuly !");
            return "redirect:/views/index";
        } else {
            System.out.println("sai tên đăng nhập mật khẩu !");
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác !");
            return "/views/login";
        }
    }
}
