package com.example.asm.API;

import com.example.asm.DTO.ProductDTO;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class ProductAdminAPI {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-product")
    public ResponseEntity<?> getAllProduct(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
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
            Optional<Product> optionalProduct = productService.findProductByMaSP(id);
            if (optionalProduct.isPresent()) {
                result.put("status", true);
                result.put("message", "Call api successfully");
                result.put("data", optionalProduct.get());
            } else {
                result.put("status", false);
                result.put("message", "Product not found");
                result.put("data", null);
            }
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call api failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save-product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            productService.addProduct(productDTO);
            result.put("status", true);
            result.put("message", "Save product successfully");
            result.put("data", productDTO);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Save product failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProductByMaSP(@PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            productService.deleteProductByMaSP(id);
            result.put("status", true);
            result.put("message", "Delete product successfully");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Delete product failed");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/search-product")
    public ResponseEntity<?> searchProduct(@RequestParam(name = "query") String query,
                                           @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "15") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        try {
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            Page<Product> page = productService.searchProductsByTenSP(query, pageable);
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
}
