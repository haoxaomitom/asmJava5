package com.example.asm.Repository;

import com.example.asm.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail,Integer> {
    @Override
    List<OrderDetail> findAll();

    List<OrderDetail> findOrderDetailByOrder_OrderId(Integer id);
    OrderDetail findOrderDetailByOrderDetailId(Integer id);
    OrderDetail save(OrderDetail orderDetail);
    void deleteOrderDetailByOrderDetailId(Integer id);
}
