package com.example.asm.DTO;

import jakarta.persistence.Column;
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

    @NotBlank(message = "Tên Sản Phẩm không được để trống")
    private String tenSP;

    @NotBlank(message = "Hãng Sản xuất không được để trống")
    private String HangSX;

    @NotNull(message = "Giá bán không được để trống")
    private double giaBan;

    @NotNull(message = "Giá góc không được để trống")
    private double giaGoc;
}
