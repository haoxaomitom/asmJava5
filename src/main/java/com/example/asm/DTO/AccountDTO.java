package com.example.asm.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @NotBlank(message = "Username is required")
    private String makh;


    @NotBlank(message = "Password is required")
    private String matkhau;


    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;


    @NotBlank(message = "Full name required")
    private String tenKH;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;


    @NotNull(message = "Gender required")
    private Boolean gioiTinh;

    @Column(name="DIACHI")
    private String diaChi;


    @NotBlank(message = "Phone number required")
    private String soDT;

    @NotBlank(message = "Confirm password number required")
    private String confirmPassword;

    @Column(name="VAITRO")
    private String vaitro;
}
