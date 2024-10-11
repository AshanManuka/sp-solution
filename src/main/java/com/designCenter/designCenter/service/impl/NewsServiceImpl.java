package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.dto.HeadLineResponse;
import com.designCenter.designCenter.dto.NewsContentReqDto;
import com.designCenter.designCenter.dto.NewsResponse;
import com.designCenter.designCenter.entity.Comment;
import com.designCenter.designCenter.entity.NewsBody;
import com.designCenter.designCenter.entity.NewsCategory;
import com.designCenter.designCenter.repository.CommentRepository;
import com.designCenter.designCenter.repository.NewsCategoryRepository;
import com.designCenter.designCenter.repository.NewsRepository;
import com.designCenter.designCenter.service.NewsService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class NewsServiceImpl implements NewsService {

    private final NewsCategoryRepository newsCategoryRepository;
    private final NewsRepository newsBodyRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public String saveNews(NewsContentReqDto reqDto) {
        log.info("Checking is request empty");
        if(reqDto == null || reqDto.getHeadLine().isEmpty() || reqDto.getNewsBody().isEmpty() || reqDto.getCategoryIdList().isEmpty()){
            return "Null Input";
        }else{
            log.info("Checking, is category empty");
            List<NewsCategory> categoryList = new ArrayList<>();

            for (int categoryId:reqDto.getCategoryIdList()) {
                NewsCategory newsCategory = newsCategoryRepository.findCategoryById(categoryId);

                if(newsCategory != null){
                    categoryList.add(newsCategory);
                }

            }

            NewsBody newsBody = NewsBody.builder()
                    .newsCategories(categoryList)
                    .createdDate(new Date())
                    .headLine(reqDto.getHeadLine())
                    .body(reqDto.getNewsBody())
                    .build();

            NewsBody savedNews = newsBodyRepository.save(newsBody);

            return savedNews.getHeadLine();
        }
    }

    @Override
    public String saveNewsCategory(String categoryName, String description) {
        log.info("Checking is request empty: {}", categoryName);
        if(categoryName.isEmpty()){
            return "Null Input";
        }else{
            NewsCategory newsCategory = newsCategoryRepository.findByCategoryName(categoryName);

            if(newsCategory == null){
                newsCategory = NewsCategory.builder()
                        .categoryName(categoryName)
                        .description(description)
                        .build();
            }
            NewsCategory savedCategory = newsCategoryRepository.save(newsCategory);

            return savedCategory.getCategoryName();
        }
    }

    @Override
    public List<HeadLineResponse> getAllHeadlineByCategory(int categoryId) {
        if(categoryId != 0){
            log.info("check is category exists: {}", categoryId);
            NewsCategory newsCategory = newsCategoryRepository.findCategoryById(categoryId);
            if(newsCategory != null){
                log.info("Getting all news headlines by category");
                return newsBodyRepository.getAllHeadLineByCategory(categoryId);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public NewsResponse getFullNewsById(int newsId) {
        log.info("check is news exists: {}", newsId);
        if(newsId != 0){
            NewsBody newsBody = newsBodyRepository.getOne(newsId);
            return modelMapper.map(newsBody,NewsResponse.class);
        }else{
            return null;
        }
    }

    @Override
    public String saveComment(String comment, int newsId) {
        log.info("check is news exists: {}", newsId);
        if(newsId != 0){
            NewsBody newsBody = newsBodyRepository.getOne(newsId);

            if(newsBody != null){

                Comment commentDesc = Comment.builder()
                        .content(comment)
                        .createdDate(new Date())
                        .news(newsBody)
                        .build();

                commentDesc = commentRepository.save(commentDesc);

                if(commentDesc.getId() != null || commentDesc.getId() != 0){
                    return commentDesc.getContent();
                }else{
                    return null;
                }
            }else{
                return null;
            }
        }else{
            return null;
        }

    }


}
