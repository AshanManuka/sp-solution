package com.designCenter.designCenter.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class NewsBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String headLine;

    @Lob
    private String body;

    private Date createdDate;

    @OneToMany(mappedBy = "comment")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<NewsCategory> newsCategories = new ArrayList<>();
}
