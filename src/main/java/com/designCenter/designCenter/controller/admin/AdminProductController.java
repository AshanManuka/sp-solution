package com.designCenter.designCenter.controller.admin;

import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.product.ProductReqDto;
import com.designCenter.designCenter.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/product")
@RequiredArgsConstructor
@Log4j2
public class AdminProductController {

    private final ProductService productService;


    @PostMapping(value = "/save")
    public ResponseEntity<?> saveProduct(@ModelAttribute ProductReqDto productRequest,
                                         @RequestParam long categoryId){
        log.info("Save new product-Name:{}",productRequest.getProductName());
        productService.saveProduct(categoryId,productRequest);
        return ResponseEntity.ok(new CommonResponse<>(true,"Product saved successfully..!"));
    }


}
