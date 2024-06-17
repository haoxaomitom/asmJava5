package com.example.asm.Service;

import com.example.asm.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional
    Page<Product> findAll(Pageable pageable);
}