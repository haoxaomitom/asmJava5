package com.example.asm.Controller;

import com.example.asm.API.ProductAPI;
import com.example.asm.DTO.ProductDTO;
import com.example.asm.Entity.Product;
import com.example.asm.Service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductAPI api;
    @Autowired
    private ProductService productService;
    @GetMapping("/detail/*")
    public String detail(HttpServletRequest request, Model model){
        String username=null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        model.addAttribute("username", username);
        return "views/detail";
    }
    @GetMapping("/admin/list")
    public String listProduct(){
        return "views/admin/product";
    }
    @GetMapping("/admin/form")
    public String formProduct(Model model){
        model.addAttribute("product", new ProductDTO());
        return "views/admin/product";
    }
    @PostMapping("/admin/delete")
    public String deleteProduct(@Valid @ModelAttribute ProductDTO productDTO,
                                BindingResult bindingResult, Model model){
        Integer id = productDTO.getMaSP();
        api.deleteProduct(id);
        return "redirect:/product/admin/form";
    }
    @PostMapping("/admin/create")
    public String createProduct(@Valid @ModelAttribute ProductDTO productDTO,
                                BindingResult bindingResult, Model model){

        Product product = new Product();
        product.setTenSP(productDTO.getTenSP());
        product.setHangSX(product.getHangSX());
        product.setHinh(productDTO.getHinh());
        product.setGiaBan(product.getGiaBan());
        product.setGiaGoc(product.getGiaGoc());
        product.setKhuyenMai(productDTO.getKhuyenMai());
        product.setSoLuong(productDTO.getSoLuong());
        api.saveProduct(product);
        System.out.println("Save()");
        return "redirect:/product/admin/form";
    }
    @PostMapping("/admin/update")
    public String updateProduct(@Valid @ModelAttribute ProductDTO productDTO,BindingResult bindingResult, Model model){

        Product product = new Product();
        product.setMaSP(productDTO.getMaSP());
        product.setTenSP(productDTO.getTenSP());
        product.setHangSX(product.getHangSX());
        product.setHinh(productDTO.getHinh());
        product.setGiaBan(product.getGiaBan());
        product.setGiaGoc(product.getGiaGoc());
        product.setKhuyenMai(productDTO.getKhuyenMai());
        product.setSoLuong(productDTO.getSoLuong());
        api.saveProduct(product);
        return "redirect:/product/admin/form";
    }
}