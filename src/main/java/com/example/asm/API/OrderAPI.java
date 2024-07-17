package com.example.asm.API;

import com.example.asm.Entity.Order;
import com.example.asm.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderAPI {
    Map<String,Object> result = new HashMap<>();
    @Autowired
    private OrderService service;
    @GetMapping("/get-all-order")
    public ResponseEntity<?> getAllOrder(){
        try {
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", service.findAllOrder());
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-order-by-makh")
    public ResponseEntity<?> getOrderByMakh(@RequestParam("maKH") String maKH){
        try {
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", service.findOrderByAccount_Makh(maKH));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-order-by-makh-and-status/{maKH}")
    public ResponseEntity<?> getOrderByMakhAndStatus(@PathVariable("maKH") String maKH){
        try {
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", service.findOrderByAccount_MakhAndStatus(maKH,false));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Order order){
        try {
            service.createOrder(order);
            result.put("status", true);
            result.put("message", "Call api successfully");

        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");

            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

}
