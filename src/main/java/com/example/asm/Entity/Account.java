package com.example.asm.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "KHACHHANG")
public class Account {
    @Id
    @Column(name="MAKH", unique=true)
    @NotBlank(message = "MaKH is required")
    private String makh;

    @Column(name="MATKHAU")
    @NotBlank(message = "Password is required")
    private String matkhau;

    @Column(name="EMAIL", unique=true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name="TENKH")
    private String tenKH;

    @Column(name="NGAYSINH")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @Column(name="GIOITINH")
    private Boolean gioiTinh;

    @Column(name="DIACHI")
    private String diaChi;

    @Column(name="SODT")
    private String soDT;
}
