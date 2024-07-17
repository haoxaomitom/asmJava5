package com.example.asm.Service.ServiceImplements;

import com.example.asm.Entity.OrderDetail;
import com.example.asm.Repository.OrderDetailRepo;
import com.example.asm.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImp implements OrderDetailService {
    @Autowired
    private OrderDetailRepo repo;
    @Override
    public List<OrderDetail> findAllOrderDetail() {
        return repo.findAll();
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrder_OrderId(Integer id) {
        return repo.findOrderDetailByOrder_OrderId(id);
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {

        return repo.save(orderDetail);
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        return repo.save(orderDetail);
    }

    @Override
    public void deleteOrderDetailById(Integer Id) {
        repo.deleteOrderDetailByOrderDetailId(Id);
    }

    @Override
    public OrderDetail findOrderDetailByOrderDetailId(Integer id) {
        return repo.findOrderDetailByOrderDetailId(id);
    }
}
