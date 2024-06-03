package com.example.asm.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Chi_tiet_san_pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaCT;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private Product product;

}
