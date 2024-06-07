package com.example.asm.Service.ServiceImplements;

import com.example.asm.Entity.Account;
import com.example.asm.Entity.Product;
import com.example.asm.Repository.AccountRepo;
import com.example.asm.Service.AccountSer;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountSerImpl implements AccountSer {
    @Autowired
    private AccountRepo repo;

    @Override
    public List<Account> findAllAccount() {
        return repo.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        repo.save(account);
    }
    @Override
    public void register(Account account) {
        String hashedPassword = BCrypt.hashpw(account.getMatkhau(), BCrypt.gensalt());
        account.setMatkhau(hashedPassword);
        repo.save(account);
    }
    @Override
    public Account findAccountByMakh(String makh) {
        return repo.findAccountByMakh(makh);
    }
}
