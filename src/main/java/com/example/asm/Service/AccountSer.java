package com.example.asm.Service;

import com.example.asm.Entity.Account;

import java.util.List;

public interface AccountSer {
    List<Account> findAllAccount();
    void saveAccount(Account account);
    void register(Account account);
    void deleteAccountByMakh(String makh);
    Account findAccountByMakh(String makh);
}
