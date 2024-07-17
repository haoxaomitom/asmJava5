package com.example.asm.API;

import com.example.asm.Entity.Account;
import com.example.asm.Entity.Order;
import com.example.asm.Entity.OrderDetail;
import com.example.asm.Entity.Product;
import com.example.asm.Service.AccountSer;
import com.example.asm.Service.OrderDetailService;
import com.example.asm.Service.OrderService;
import com.example.asm.Service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddToCartAPI {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountSer accountSer;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ProductService productService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {

            String username = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        username = cookie.getValue();
                        break;
                    }
                }
            }
            Order order = orderService.findOrderByAccount_MakhAndStatus(username, false);
            if (order == null) {
                Account account = accountSer.findAccountByMakh(username);
                order = new Order();
                order.setStatus(false);
                order.setAccount(account);
                orderService.createOrder(order);
            }

            // Tạo OrderDetail và lưu vào database
            Optional<Product> productOptional = productService.findProductByMaSP(productId);
            if (!productOptional.isPresent()) {
                throw new RuntimeException("Không tìm thấy sản phẩm với mã sản phẩm: " + productId);
            }

            Product product = productOptional.get();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(product.getGiaBan() * (1 - product.getKhuyenMai()));

            orderDetailService.createOrderDetail(orderDetail);

            result.put("status", true);
            result.put("message", "Thêm vào giỏ hàng thành công");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", false);
            result.put("message", "Thêm vào giỏ hàng thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
