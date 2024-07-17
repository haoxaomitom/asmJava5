package com.example.asm.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Chi_tiet_don_hang")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderDetailId")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_chi_tiet_don_hang")
    private Integer orderDetailId;

    @ManyToOne
    @JoinColumn(name = "Ma_don_hang")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "Ma_san_pham")
    private Product product;

    @Column(name = "So_luong")
    private int quantity;

    @Column(name = "Gia")
    private double price;
}
