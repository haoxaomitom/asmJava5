package com.example.asm.Service.ServiceImplements;

import com.example.asm.DTO.ProductDTO;
import com.example.asm.Entity.Product;
import com.example.asm.Repository.ProductRepo;
import com.example.asm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo repo;

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Product> findProductByMaSP(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product = convertToProduct(productDTO);
        return repo.save(product);
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        Optional<Product> optionalProduct = repo.findById(productDTO.getMaSP());
        if (optionalProduct.isPresent()) {
            Product product = convertToProduct(productDTO);
            product.setMaSP(productDTO.getMaSP());
            return repo.save(product);
        }
        // Handle the case when product is not found (you can throw an exception)
        return null;
    }

    @Override
    public void deleteProductByMaSP(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<Product> searchProductsByTenSP(String tenSP, Pageable pageable) {
        return repo.findByTenSPContainingIgnoreCase(tenSP, pageable);
    }

    private Product convertToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setTenSP(productDTO.getTenSP());
        product.setHangSX(productDTO.getHangSX());
        product.setHinh(productDTO.getHinh());
        product.setGiaBan(productDTO.getGiaBan());
        product.setGiaGoc(productDTO.getGiaGoc());
        product.setKhuyenMai(productDTO.getKhuyenMai());
        product.setSoLuong(productDTO.getSoLuong());
        return product;
    }
}
