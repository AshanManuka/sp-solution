package com.designCenter.designCenter.dto;

import com.designCenter.designCenter.entity.Comment;
import lombok.*;

import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewsResponse {
    private int id;
    private String headLine;
    @Lob
    private String body;
    private Date createdDate;
    List<String> comments;
}
