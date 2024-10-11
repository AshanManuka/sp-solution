package com.designCenter.designCenter.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String categoryName;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private NewsBody news;
}
