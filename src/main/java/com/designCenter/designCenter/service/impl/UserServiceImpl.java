package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.constant.CommonConstant;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.dto.user.UserReqDto;
import com.designCenter.designCenter.dto.user.UserResDto;
import com.designCenter.designCenter.entity.User;
import com.designCenter.designCenter.enums.ActiveStatus;
import com.designCenter.designCenter.repository.UserRepository;
import com.designCenter.designCenter.service.UserService;
import com.designCenter.designCenter.util.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.designCenter.designCenter.constant.CommonConstant.INVALID_FILE_TYPE;


@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final FileHandler fileHandler;

    @Override
    public UserResDto userSignUp(UserReqDto reqDto) throws IOException {
        User user = userRepository.findByEmail(reqDto.getEmail());
        log.info("Checking, is email already exists..");
        if(user != null) throw new CustomServiceException(CommonConstant.DuplicateConstants.EMAIL_ALREADY_EXIST);

        user = User.builder()
                .name(reqDto.getName())
                .email(reqDto.getEmail())
                .gender(reqDto.getGender())
                .postalCode(reqDto.getPostalCode())
                .mobile(reqDto.getMobile())
                .password(reqDto.getPassword())
                .registered(new Date())
                .updated(new Date())
                .status(ActiveStatus.PENDING)
                .build();

        if(reqDto.getProfileImage() != null && !reqDto.getProfileImage().isEmpty()){
            user.setProfileImageUrl(setImage(reqDto.getProfileImage(),reqDto.getName()));
        }

        User savedUser = userRepository.save(user);
        log.info("Saving User email:{} by Id:{} ",savedUser.getEmail(),savedUser.getId());
        return this.modelMapper.map(savedUser,UserResDto.class);
    }




    private String setImage(MultipartFile file, String name){
        String logoFileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        assert logoFileExtension != null;
        if (logoFileExtension.equalsIgnoreCase("PNG") || logoFileExtension.equalsIgnoreCase("JPG") || logoFileExtension.equalsIgnoreCase("JPEG") || logoFileExtension.equalsIgnoreCase("PDF")) {
            return fileHandler.uploadToS3Bucket(file, (name+ UUID.randomUUID()).replaceAll("[-/+\\s^%@<>!#*.,~$\\\\]", "-"));
        } else {
            throw new CustomServiceException(INVALID_FILE_TYPE);
        }
    }
}
