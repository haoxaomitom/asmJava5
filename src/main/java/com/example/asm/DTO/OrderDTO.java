package com.example.asm.DTO;

import com.example.asm.Entity.Account;
import com.example.asm.Entity.OrderDetail;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer orderId;

    private Account account;

    private double totalPrice;

    private boolean status;

    private List<OrderDetail> orderDetails;
}
