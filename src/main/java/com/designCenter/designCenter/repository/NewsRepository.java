package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.NewsBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsBody, Integer> {

    @Query(value = "SELECT n.id, n.headLine FROM NewsBody n WHERE n.newsCategories=?1")
    List<com.designCenter.designCenter.dto.HeadLineResponse> getAllHeadLineByCategory(int categoryId);
}
