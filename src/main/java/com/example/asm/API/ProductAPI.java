package com.example.asm.API;

import com.example.asm.Entity.Product;
import com.example.asm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-product")
    public ResponseEntity<?> getAllProduct(){
        Map<String, Object> result = new HashMap<>();
        try {
            List<Product> listPro = productService.findAll();
            result.put("status",true);
            result.put("message", "Call api successfully");
            result.put("data", listPro);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data" , null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Integer id){
        Map<String, Object> result = new HashMap<>();
        try {
            Product product = productService.findProductByMaSP(id);
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", product);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data" , null);
        }
        return ResponseEntity.ok(result);
    }
}
