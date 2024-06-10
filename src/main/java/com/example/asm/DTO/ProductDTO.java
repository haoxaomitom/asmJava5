package com.example.asm.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int maSP;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String tenSP;

    @NotBlank(message = "Hãng sản xuất không được để trống")
    private String hangSX;

    @NotNull(message = "Giá bán không được để trống")
    private double giaBan;

    @NotNull(message = "Giá góc không được để trống")
    private double giaGoc;

    @NotNull(message = "số lượng không được để trống")
    private int soLuong;

    private double khuyenMai;

    private String hinh;

}