package com.designCenter.designCenter.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewsContentReqDto {
    private String headLine;
    private String newsBody;
    private List<Integer> categoryIdList;
}
