package com.example.asm.Controller;

import com.example.asm.DTO.AccountDTO;
import com.example.asm.Entity.Account;
import com.example.asm.Repository.AccountRepo;
import com.example.asm.Service.AccountSer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountSer accountService;

    @Autowired
    private AccountRepo repo;

    @GetMapping("/form")
    public String User(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("username", username);
        return "/views/user";
    }

    @PostMapping("/submit")
    public String save(@Valid @ModelAttribute("account") AccountDTO accountDTO, BindingResult bindingResult, Model model) {
        // Kiểm tra lỗi xác thực
        if (bindingResult.hasErrors()) {
            System.out.println("Nhập dữ liệu không hợp lệ !");
            return "/views/user";
        }

        // Kiểm tra tài khoản đã tồn tại dựa trên makh
//        Account existingAccount = repo.findAccountByMakh(accountDTO.getMakh());
//        if (existingAccount != null) {
//            bindingResult.rejectValue("makh", "error.account", "Đã có tài khoản đăng ký với mã khách hàng này");
//            System.out.println("Error code: 1 !");
//            return "/views/user";
//        }

        // Kiểm tra tài khoản đã tồn tại dựa trên email
//        Account existingEmail = repo.findAccountByEmail(accountDTO.getEmail());
//        if (existingEmail != null) {
//            bindingResult.rejectValue("email", "error.account", "Đã có tài khoản đăng ký với email này");
//            return "/views/user";
//        }
        if (!accountDTO.getMatkhau().equals(accountDTO.getConfirmPassword())){
            bindingResult.rejectValue("matkhau", "error.account", "Mật khẩu và xác nhận mật khẩu không khớp nhau");
            System.out.println("Error code: 2");
            return "/views/user";
        }
        Account acc = new Account();
        acc.setMakh(accountDTO.getMakh());
        acc.setMatkhau(accountDTO.getMatkhau());
        acc.setEmail(accountDTO.getEmail());
        acc.setTenKH(accountDTO.getTenKH());
        acc.setNgaySinh(accountDTO.getNgaySinh());
        acc.setGioiTinh(accountDTO.getGioiTinh());
        acc.setDiaChi(accountDTO.getDiaChi());
        acc.setSoDT(accountDTO.getSoDT());
        // Đăng ký tài khoản mới
        accountService.register(acc);
        System.out.println("Thay đổi dữ liệu thành công !");
        return "redirect:/user/form";
    }
}
