package com.example.asm.Service;

import com.example.asm.Entity.OrderDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDetailService {
    @Transactional(readOnly = true)
    List<OrderDetail> findAllOrderDetail();
    @Transactional(readOnly = true)
    List<OrderDetail> findOrderDetailByOrder_OrderId(Integer id);
    @Transactional
    OrderDetail createOrderDetail(OrderDetail orderDetail);
    @Transactional
    OrderDetail updateOrderDetail(OrderDetail orderDetail);
    @Transactional
    void deleteOrderDetailById(Integer id);
    @Transactional
    OrderDetail findOrderDetailByOrderDetailId(Integer id);
}
