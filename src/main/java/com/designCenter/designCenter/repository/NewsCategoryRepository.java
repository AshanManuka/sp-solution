package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Integer> {


    @Query(value = "SELECT c FROM NewsCategory c WHERE c.categoryName=?1")
    NewsCategory findByCategoryName(String categoryName);

    @Query(value = "SELECT c FROM NewsCategory c WHERE c.id=?1")
    NewsCategory findCategoryById(int categoryId);
}
