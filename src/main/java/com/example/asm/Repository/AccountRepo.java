package com.example.asm.Repository;

import com.example.asm.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
    Account findAccountByMakh(String makh);

    Account findAccountByEmail(String email);

    List<Account> findAll();

    void deleteAccountByMakh(String makh);


}
