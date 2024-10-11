package com.designCenter.designCenter.controller.admin;

import com.designCenter.designCenter.dto.category.CategoryReqDto;
import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/category")
@RequiredArgsConstructor
@Log4j2
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> CreateCategory(@ModelAttribute CategoryReqDto reqDto){
        log.info("Create new category name:{}",reqDto.getCategoryName());
        categoryService.CreateCategory(reqDto);
        return ResponseEntity.ok(new CommonResponse<>(true,"Category Saved Successfully..!"));
    }


}
