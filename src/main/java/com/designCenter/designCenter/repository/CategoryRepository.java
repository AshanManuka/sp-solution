package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT c FROM Category c WHERE c.categoryName=?1")
    Category findByCategoryName(String categoryName);
}
