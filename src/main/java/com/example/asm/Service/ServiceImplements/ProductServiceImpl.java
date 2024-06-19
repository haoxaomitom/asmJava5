package com.example.asm.Service.ServiceImplements;

import com.example.asm.Entity.Product;
import com.example.asm.Repository.ProductRepo;
import com.example.asm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public void deleteProductByMaSP(Integer id) {
        repo.deleteAllByMaSP(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<Product> searchProductsByTenSP(String tenSP, Pageable pageable) {
        return repo.findByTenSPContainingIgnoreCase(tenSP, pageable);
    }
}
