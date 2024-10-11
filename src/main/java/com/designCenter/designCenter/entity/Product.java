package com.designCenter.designCenter.entity;

import com.designCenter.designCenter.enums.Size;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productName;
    private double price;

    @Enumerated(value = EnumType.STRING)
    private Size size;
    private double discount;
    private String productImage;
    private String productCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Category category;

}
