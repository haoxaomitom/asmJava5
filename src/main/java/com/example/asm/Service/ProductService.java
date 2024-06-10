package com.example.asm.Service;

import com.example.asm.DTO.ProductDTO;
import com.example.asm.Entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    @Transactional(readOnly = true)
    List<Product> findAll();
    @Transactional
    Product findProductByMaSP(Integer id);
    @Transactional
    Product addProduct(Product product);
    @Transactional
    void deleteProductByMaSP(Integer id);
}
