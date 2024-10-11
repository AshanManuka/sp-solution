package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.dto.category.CategoryReqDto;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.entity.Category;
import com.designCenter.designCenter.repository.CategoryRepository;
import com.designCenter.designCenter.service.CategoryService;
import com.designCenter.designCenter.util.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

import static com.designCenter.designCenter.constant.CommonConstant.INVALID_FILE_TYPE;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileHandler fileHandler;

    @Override
    public void CreateCategory(CategoryReqDto reqDto) {
        Category category = categoryRepository.findByCategoryName(reqDto.getCategoryName());
        if(category != null) throw new CustomServiceException("Category already Exists..!");
        log.info("Saving new Category-Name:{}",reqDto.getCategoryName());
        category = Category.builder()
                .categoryName(reqDto.getCategoryName())
                .created(new Date())
                .updated(new Date())
                .build();
        if(reqDto.getCategoryIcon() != null && !reqDto.getCategoryIcon().isEmpty()){
            category.setCategoryIcon(setImage(reqDto.getCategoryIcon(),reqDto.getCategoryName()));
        }
        categoryRepository.save(category);
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
