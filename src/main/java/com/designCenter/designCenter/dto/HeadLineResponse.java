package com.designCenter.designCenter.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HeadLineResponse {
    private int newsId;
    private String headline;
}
