package com.designCenter.designCenter.entity;

import com.designCenter.designCenter.enums.ActiveStatus;
import com.designCenter.designCenter.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mobile;
    private String postalCode;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String profileImageUrl;
    private String email;
    private String password;
    private Date registered;
    private Date updated;
    @Enumerated(value = EnumType.STRING)
    private ActiveStatus status;
}
