package com.example.asm.Service.ServiceImplements;

import com.example.asm.DTO.ProductDTO;
import com.example.asm.Entity.DetailProduct;
import com.example.asm.Entity.Product;
import com.example.asm.Repository.ProductRepo;
import com.example.asm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}