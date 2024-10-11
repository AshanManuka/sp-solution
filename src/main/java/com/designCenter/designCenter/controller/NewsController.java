package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.HeadLineResponse;
import com.designCenter.designCenter.dto.NewsContentReqDto;
import com.designCenter.designCenter.dto.NewsResponse;
import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/public")
public class NewsController {

    private final NewsService newsService;

    @PostMapping(value = "/save-category")
    public ResponseEntity<?> saveCategory(@RequestParam String categoryName, @RequestParam String description) throws IOException {
        log.info("Getting request for new category: {}",categoryName);
        String categoryRes = newsService.saveNewsCategory(categoryName, description);
        return ResponseEntity.ok(new CommonResponse<>(true,categoryRes+" saved Successfully..!"));
    }


    @PostMapping(value = "/save-news")
    public ResponseEntity<?> saveNews(@RequestBody NewsContentReqDto reqDto) throws IOException {
        log.info("Getting request for new news");
        String newsRes = newsService.saveNews(reqDto);
        return ResponseEntity.ok(new CommonResponse<>(true,newsRes+" saved Successfully..!"));
    }

    @GetMapping(value = "/headline-by-category")
    public ResponseEntity<?> getHeadlineByCategory(@RequestParam int categoryId) throws IOException {
        log.info("Getting request for headlines");
        List<HeadLineResponse> headlineList= newsService.getAllHeadlineByCategory(categoryId);
        return ResponseEntity.ok(new CommonResponse<>(true,headlineList));
    }

    @GetMapping(value = "/news-by-id")
    public ResponseEntity<?> getnewsById(@RequestParam int newsId) throws IOException {
        log.info("Getting request for full news");
        NewsResponse newsResponse= newsService.getFullNewsById(newsId);
        return ResponseEntity.ok(new CommonResponse<>(true,newsResponse));
    }

    @PostMapping(value = "/save-comment")
    public ResponseEntity<?> saveComment(@RequestParam String comment, @RequestParam int newsId) throws IOException {
        log.info("Getting request for new comment");
        String commentRes = newsService.saveComment(comment, newsId);
        return ResponseEntity.ok(new CommonResponse<>(true,commentRes+" saved Successfully..!"));
    }










}
