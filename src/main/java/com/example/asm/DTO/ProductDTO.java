package com.example.asm.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int maSP;
    private String tenSP;
    private String hangSX;
    private double giaBan;
    private double giaGoc;
    private int soLuong;
    private double khuyenMai;
    private String hinh;
    private List<DetailProductDTO> detailProducts;
}