package com.example.asm.DTO;

import com.example.asm.Entity.Product;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Integer orderDetailId;
    private Integer orderId;
    private Product product;
    private int quantity;

}
