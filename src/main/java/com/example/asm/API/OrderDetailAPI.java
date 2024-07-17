package com.example.asm.API;


import com.example.asm.Entity.OrderDetail;
import com.example.asm.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderDetailAPI {
    Map<String,Object> result = new HashMap<>();
    @Autowired
    private OrderDetailService service;


    @GetMapping("/get-all-order-detail")
    public ResponseEntity<?> getAllOrderDetail(){
        try {
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", service.findAllOrderDetail());
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-order-detail-by-orderId")
    public ResponseEntity<?> getOrderDetailByOrderId(@RequestParam("id") Integer id){
        try {
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", service.findOrderDetailByOrder_OrderId(id));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/save-order-detail")
    public ResponseEntity<?> saveOrderDetail(@RequestBody OrderDetail orderDetail){
        try {

            service.createOrderDetail(orderDetail);
            result.put("status", true);
            result.put("message", "Thêm vào giỏ hàng thành công");

        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Thêm vào giỏ hàng thất bại");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @PutMapping("/update-order-detail")
    public ResponseEntity<?> updateOrderDetail(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity){
        OrderDetail orderDetail = service.findOrderDetailByOrderDetailId(id);
        orderDetail.setQuantity(quantity);
        try {
            service.createOrderDetail(orderDetail);
            result.put("status", true);
            result.put("message", "Thêm vào giỏ hàng thành công");

        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Thêm vào giỏ hàng thất bại");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-order-detail")
    public ResponseEntity<?> deleteOrderDetail(@RequestParam("id") Integer id){
        try {
            service.deleteOrderDetailById(id);
            result.put("status", true);
            result.put("message", "Xóa thành công");

        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Xóa thất bại");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

}
