package com.example.asm.Repository;

import com.example.asm.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    @Override
    List<Order> findAll();

    void deleteOrderByAccount_Makh(String maKH);
    Order save(Order order);
    Order findOrderByAccount_Makh(String maKH);
    Order findOrderByAccount_MakhAndStatus(String maKH, boolean status);
    Order findOrderByStatus(Boolean status);
}
