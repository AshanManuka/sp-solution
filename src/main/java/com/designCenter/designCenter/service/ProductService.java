package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.product.ProductReqDto;

public interface ProductService {
    void saveProduct(long categoryId, ProductReqDto productRequest);
}
