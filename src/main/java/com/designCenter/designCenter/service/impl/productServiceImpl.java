package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.dto.product.ProductReqDto;
import com.designCenter.designCenter.entity.Category;
import com.designCenter.designCenter.entity.Product;
import com.designCenter.designCenter.repository.CategoryRepository;
import com.designCenter.designCenter.repository.ProductRepository;
import com.designCenter.designCenter.service.ProductService;
import com.designCenter.designCenter.util.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.designCenter.designCenter.constant.CommonConstant.INVALID_FILE_TYPE;

@Service
@RequiredArgsConstructor
@Log4j2
public class productServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final FileHandler fileHandler;


    @Override
    public void saveProduct(long categoryId, ProductReqDto productRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CustomServiceException("No Category Found..!"));

        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .category(category)
                .price(productRequest.getPrice())
                .discount(productRequest.getDiscount())
                .size(productRequest.getSize())
                .productCode(UUID.randomUUID().toString().substring(1,5)+"-"+productRequest.getProductName())
                .build();
        if(productRequest.getProductImage() != null && !productRequest.getProductImage().isEmpty()){
            product.setProductImage(setImage(productRequest.getProductImage(),productRequest.getProductName()));
        }

        log.info("Saving new product- productCode:{}",product.getProductCode());
        productRepository.save(product);
    }



    private String setImage(MultipartFile file, String name){
        String logoFileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        assert logoFileExtension != null;
        if (logoFileExtension.equalsIgnoreCase("PNG") || logoFileExtension.equalsIgnoreCase("JPG") || logoFileExtension.equalsIgnoreCase("JPEG") || logoFileExtension.equalsIgnoreCase("PDF")) {
            return fileHandler.uploadToS3Bucket(file, (name+ UUID.randomUUID()).replaceAll("[-/+\\s^%@<>!#*.,~$\\\\]", "-"));
        } else {
            throw new CustomServiceException(INVALID_FILE_TYPE);
        }
    }
}
