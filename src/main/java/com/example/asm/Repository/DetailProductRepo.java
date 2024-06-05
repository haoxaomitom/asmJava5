package com.example.asm.Repository;

import com.example.asm.Entity.DetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProductRepo extends JpaRepository<DetailProduct, Integer> {
    List<DetailProduct> findAll();
}