package com.example.asm.Service.ServiceImplements;

import com.example.asm.Entity.Account;
import com.example.asm.Repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByMakh(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getMakh())
                .password(account.getMatkhau())
                .roles(account.getVaitro()) // Assuming vaitro is storing roles like "ADMIN", "USER", etc.
                .build();
    }
}
