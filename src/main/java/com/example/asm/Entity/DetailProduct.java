package com.example.asm.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "CHI_TIET_SAN_PHAM")
@NoArgsConstructor
@AllArgsConstructor
public class DetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private int maCT;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    @JsonBackReference
    private Product product;

    @Column(name = "Hinh")
    private String hinh;

    @Column(name = "Mau")
    private String mau;
}
