package com.example.asm.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Don_hang")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_don_hang")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "Ma_khach_hang")
    @JsonBackReference
    private Account account;

    @Column(name = "Tong_gia")
    private double totalPrice;

    @Column(name = "Trang_thai")
    private boolean status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;
}
