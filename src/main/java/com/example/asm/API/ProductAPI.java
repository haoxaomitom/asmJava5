package com.example.asm.API;

import com.example.asm.Entity.Product;
import com.example.asm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-product")
    public ResponseEntity<?> getAllProduct(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        try {
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize); // Correct page number calculation

            Page<Product> page = productService.findAll(pageable);
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", page.getContent());
            result.put("totalPages", page.getTotalPages());
            result.put("currentPage", pageNo);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Product product = productService.findProductByMaSP(id);
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", product);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save-product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        Map<String, Object> result = new HashMap<>();
        try {
            Product products = productService.addProduct(product);
            result.put("status", true);
            result.put("message", "Call api successfully");
            result.put("data", products);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        Product existingProduct = productService.findProductByMaSP(id);

        try {
            if (existingProduct != null) {
                productService.deleteProductByMaSP(id);
                result.put("status", true);
                result.put("message", "Call api successfully");

            } else {
                result.put("status", true);
                result.put("message", "Call api successfully but id not existing");
                result.put("data", null);
            }
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
}
