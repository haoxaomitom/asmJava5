package com.example.asm.Service;

import com.example.asm.DTO.ProductDTO;
import com.example.asm.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    @Transactional(readOnly = true)
    List<Product> findAll();

    @Transactional
    Optional<Product> findProductByMaSP(Integer id);
    @Transactional
//    Product addProduct(Product product);

    Product addProduct(ProductDTO productDTO);
    @Transactional
    Product updateProduct(ProductDTO productDTO);

    @Transactional
    void deleteProductByMaSP(Integer id);
    @Transactional
    Page<Product> findAll(Pageable pageable);

    Page<Product> searchProductsByTenSP(String tenSP, Pageable pageable);

}