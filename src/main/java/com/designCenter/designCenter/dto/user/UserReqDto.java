package com.designCenter.designCenter.dto.user;

import com.designCenter.designCenter.enums.Gender;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserReqDto {
    private String name;
    private String mobile;
    private String postalCode;
    private Gender gender;
    private MultipartFile profileImage;
    private String email;
    private String password;

}
