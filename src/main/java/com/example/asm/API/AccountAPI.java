package com.example.asm.API;

import com.example.asm.Entity.Account;
import com.example.asm.Service.AccountSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountAPI {
    @Autowired
    private AccountSer accountSer;
    @GetMapping("/get-all-acc")
    public ResponseEntity<?> getAllAcc(){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("status",true);
            result.put("message","Call api success");
            result.put("data",accountSer.findAllAccount());
        }catch (Exception e){
            result.put("status",false);
            result.put("message","Call api failed");
            result.put("data",null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-acc-by-makh")
    public ResponseEntity<?> getAccByMakh(@RequestParam("maKH") String makh){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("status",true);
            result.put("message","Call api success");
            result.put("data",accountSer.findAccountByMakh(makh));
        }catch (Exception e){
            result.put("status",false);
            result.put("message","Call api failed");
            result.put("data",null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/save-acc")
    public ResponseEntity<?> saveAcc(@RequestBody Account account){
        Map<String,Object> result = new HashMap<>();
        try {
            accountSer.saveAccount(account);
            result.put("status",true);
            result.put("message","Call api success");

        }catch (Exception e){
            result.put("status",false);
            result.put("message","Call api failed");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/delete-acc-by-makh")
    public ResponseEntity<?> deleteAccByMakh(@RequestParam("maKH") String makh){
        Map<String,Object> result = new HashMap<>();
        try {
            accountSer.deleteAccountByMakh(makh);
            result.put("status",true);
            result.put("message","Call api success");

        }catch (Exception e){
            result.put("status",false);
            result.put("message","Call api failed");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

}
