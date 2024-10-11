package com.designCenter.designCenter.dto.user;

import com.designCenter.designCenter.enums.ActiveStatus;
import com.designCenter.designCenter.enums.Gender;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResDto {
    private Long id;
    private String name;
    private String mobile;
    private String postalCode;
    private Gender gender;
    private String profileImage;
    private String email;
    private String password;
    private Date registered;
    private Date updated;
    private ActiveStatus status;
}
