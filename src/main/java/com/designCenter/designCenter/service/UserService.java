package com.designCenter.designCenter.service;


import com.designCenter.designCenter.dto.user.UserReqDto;
import com.designCenter.designCenter.dto.user.UserResDto;

import java.io.IOException;

public interface UserService {

    UserResDto userSignUp(UserReqDto reqDto) throws IOException;
}
