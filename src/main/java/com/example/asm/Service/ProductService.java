package com.example.asm.Service;

import com.example.asm.Entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findProductByMaSP(Integer id);
}
