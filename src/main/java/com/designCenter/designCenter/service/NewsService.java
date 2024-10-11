package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.HeadLineResponse;
import com.designCenter.designCenter.dto.NewsContentReqDto;
import com.designCenter.designCenter.dto.NewsResponse;

import java.util.List;

public interface NewsService {

    String saveNews(NewsContentReqDto reqDto);

    String saveNewsCategory(String categoryName, String description);

    List<HeadLineResponse> getAllHeadlineByCategory(int categoryId);

    NewsResponse getFullNewsById(int newsId);

    String saveComment(String comment, int newsId);
}
