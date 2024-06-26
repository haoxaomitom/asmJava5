package com.example.asm.Repository;

import com.example.asm.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    Product findProductByMaSP(Integer id);

    Product save(Product product);

    void deleteAllByMaSP(Integer id);

    @Override
    Page<Product> findAll(Pageable pageable);

    Page<Product> findByTenSPContainingIgnoreCase(String tenSP, Pageable pageable);
}