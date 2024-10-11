package com.designCenter.designCenter.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import eu.medsea.mimeutil.MimeUtil2;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

@Component
@Log4j2
@RequiredArgsConstructor
public class FileHandler {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

     private final AmazonS3 s3Client;

    public String uploadToS3Bucket(MultipartFile file, String name) {
        try {
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (file.getSize() > 15000000) throw new CustomServiceException("LARGE_FILE_SIZE");

            assert fileExtension != null;

            MimeUtil2 mimeUtil = new MimeUtil2();
            mimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            String mimeType = MimeUtil2.getMostSpecificMimeType(mimeUtil.getMimeTypes(inputStream)).toString();

            log.info("mimeType : " + mimeType);
            log.info("file.getContentType() : " + file.getContentType());
            log.info("FilenameUtils.getExtension(file.getOriginalFilename()) : " + FilenameUtils.getExtension(file.getOriginalFilename()));

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            String fileName =  name +"."+ fileExtension;
            s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
            String fileUrl = bucketName + fileName;
            log.info("fileUrl: {}", fileUrl);
            return fileName;
        } catch (IOException e) {
            log.trace("Error occurred while uploading image to s3: {}", e.getMessage());
            return "Optional.empty()";
        }
    }


}