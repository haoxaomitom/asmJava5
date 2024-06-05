package com.example.asm.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="SANPHAM")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class    Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maSP;

    @Column(name = "TENSP")
    private String tenSP;

    @Column(name = "HANGSX")
    private String HangSX;

    @Column(name = "GIABAN")
    private double giaBan;

    @Column(name = "GIAGOC")
    private double giaGoc;

    @Column(name = "SOLUONG")
    private int soLuong;

    @Column(name = "KHUYENMAI")
    private double khuyenMai;

    @Column(name = "Hinh")
    private String hinh;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetailProduct> detailProducts;
}
