package com.example.asm.Service;

import com.example.asm.Entity.Account;
import com.example.asm.Repository.AccountRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Authenticate {

    private final AccountRepo accountRepository;

    @Autowired
    public Authenticate(AccountRepo accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean authenticate(String makh, String matkhau) {
        Account foundAccount = accountRepository.findByMakh(makh);
        if (foundAccount == null) {
            System.out.println("Tài khoản không tồn tại");
            return false ;
        }
        return BCrypt.checkpw(matkhau, foundAccount.getMatkhau());
    }
}
