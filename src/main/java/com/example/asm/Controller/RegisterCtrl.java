package com.example.asm.Controller;

import com.example.asm.Entity.Account;
import com.example.asm.Entity.User;
import com.example.asm.Repository.AccountRepo;
import com.example.asm.Service.AccountSer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterCtrl {

    @Autowired
    private AccountSer accountService;
    @Autowired
    private AccountRepo repo;
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new Account());
        return "/views/register";
    }

    @PostMapping("/register/submit")
    public String save(@Valid @ModelAttribute("register") Account acc, BindingResult bindingResult, Model model) {
        Account existingAccount = repo.findByMakh(acc.getMakh());
        Account exitstingEmail = repo.findByEmail(acc.getEmail());
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (existingAccount != null) {
            model.addAttribute("makh", "Username này đã tồn tại !");
            return "register";
        }
        if (exitstingEmail != null) {
            model.addAttribute("email", "Email này đã tồn tại !");
            return "register";
        }
        accountService.register(acc);
        return "redirect:/index";
    }

}
