package com.designCenter.designCenter.dto.category;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryReqDto {
    private String categoryName;
    private MultipartFile categoryIcon;
}
