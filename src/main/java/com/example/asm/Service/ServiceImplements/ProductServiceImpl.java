package com.example.asm.Service.ServiceImplements;

import com.example.asm.Entity.Product;
import com.example.asm.Repository.ProductRepo;
import com.example.asm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo repo;

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Product findProductByMaSP(Integer id) {
        return repo.findProductByMaSP(id);
    }
}
