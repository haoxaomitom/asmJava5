package com.example.asm.Service;


import com.example.asm.Entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional
    List<Order> findAllOrder();
    @Transactional
    Order findOrderByAccount_Makh(String maKH);
    @Transactional
    void deleteOrderByAccount_Makh(String maKH);
    @Transactional
    void createOrder(Order order);
    @Transactional
    void updateOrderByAccount_Makh(Order order);
    @Transactional
    Order findOrderByAccount_MakhAndStatus(String maKH, boolean status);
    @Transactional
    Order findOrderByStatus(Boolean status);

}
