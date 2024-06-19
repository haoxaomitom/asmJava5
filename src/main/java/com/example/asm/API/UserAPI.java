package com.example.asm.API;

import com.example.asm.Entity.Account;
import com.example.asm.Service.AccountSer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserAPI {

    @Autowired
    private AccountSer accountService;

    @GetMapping("/get-all-user")
    public ResponseEntity<?> getAllAccount() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Account> listPro = accountService.findAllAccount();
            result.put("status", true);
            result.put("message", "Call API successfully");
            result.put("data", listPro);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call API failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-user-by-session")
    public ResponseEntity<?> getUserBySession(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            System.out.println(username);
            if (username != null) {
                Account acc = accountService.findAccountByMakh(username);
                if (acc != null) {
                    result.put("status", true);
                    result.put("message", "Call API successfully");
                    result.put("data", acc);
                } else {
                    result.put("status", false);
                    result.put("message", "User not found");
                    result.put("data", null);
                }
            } else {
                result.put("status", false);
                result.put("message", "No username found in session");
                result.put("data", null);
            }
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call API failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
