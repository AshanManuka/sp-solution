package com.designCenter.designCenter.dto.product;

import com.designCenter.designCenter.enums.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductReqDto {
    private String productName;
    private double price;
    private Size size;
    private double discount;
    private MultipartFile productImage;
}
