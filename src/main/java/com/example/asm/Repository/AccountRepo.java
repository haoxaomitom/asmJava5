package com.example.asm.Repository;

import com.example.asm.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
    Account findByMakh(String makh);
    Account findByEmail(String email);
}
