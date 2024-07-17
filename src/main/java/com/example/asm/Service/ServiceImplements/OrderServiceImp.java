package com.example.asm.Service.ServiceImplements;

import com.example.asm.Entity.Order;
import com.example.asm.Repository.OrderRepo;
import com.example.asm.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepo repo;
    @Override
    public List<Order> findAllOrder() {
        return repo.findAll();
    }

    @Override
    public Order findOrderByAccount_Makh(String maKH) {
        return repo.findOrderByAccount_Makh(maKH);
    }

    @Override
    public void deleteOrderByAccount_Makh(String maKH) {
        repo.deleteOrderByAccount_Makh(maKH);
    }

    @Override
    public void createOrder(Order order) {
        repo.save(order);
    }

    @Override
    public void updateOrderByAccount_Makh(Order order) {
        repo.save(order);
    }

    @Override
    public Order findOrderByAccount_MakhAndStatus(String maKH, boolean status) {
        return repo.findOrderByAccount_MakhAndStatus(maKH,status);
    }

    @Override
    public Order findOrderByStatus(Boolean status) {
        return repo.findOrderByStatus(status);
    }
}
