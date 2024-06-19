package com.example.asm.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/admin/login/form", "/admin/login/submit").permitAll()
                        .requestMatchers("/product/admin/**").hasRole("admin")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/admin/login/form")
                        .loginProcessingUrl("/admin/login/submit")
                        .usernameParameter("makh")
                        .passwordParameter("matkhau")
                        .defaultSuccessUrl("/product/admin/form", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID", "username") // Xóa cookie có tên là "username"
                        .logoutSuccessUrl("/login/form")
                        .permitAll()
                )

                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/admin/login/access-denied")
                );

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        // For demonstration purposes, create in-memory users with and without roles
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin")
//                .password("{noop}1") // {noop} means no password encoder
//                .roles("ADMIN")
//                .build());
//        manager.createUser(User.withUsername("user")
//                .password("{noop}1")
//                .roles() // No roles assigned
//                .build());
//        return manager;
//    }
}
